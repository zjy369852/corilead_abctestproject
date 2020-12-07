package com.createP;

import com.custom.customException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wt.fc.*;
import wt.iba.definition.litedefinition.AttributeDefDefaultView;
import wt.iba.definition.service.IBADefinitionHelper;
import wt.iba.value.AbstractValue;
import wt.iba.value.IBAHolder;
import wt.iba.value.StringValue;
import wt.inf.container.LookupSpec;
import wt.inf.container.WTContainerHelper;
import wt.inf.container.WTContainerRef;
import wt.inf.container.WTContainerTemplate;
import wt.inf.library.WTLibrary;
import wt.inf.template.ContainerTemplateHelper;
import wt.inf.template.WTContainerTemplateMaster;
import wt.org.WTOrganization;
import wt.org.WTPrincipal;
import wt.pdmlink.PDMLinkProduct;
import wt.pds.StatementSpec;
import wt.query.QueryException;
import wt.query.QuerySpec;
import wt.query.SearchCondition;
import wt.session.SessionHelper;
import wt.util.WTException;
import wt.util.WTPropertyVetoException;

public class productLibrary {
    private static final Logger logger = LoggerFactory.getLogger(productLibrary.class);

    /**
     * 创建产品库
     * @param wtObject
     * @param ibaName
     * @return
     * @throws Exception
     */
    public static PDMLinkProduct CreateProduct(WTObject wtObject, String ibaName) throws Exception {
        IBAHolder holder= (IBAHolder) wtObject;
        String strName = getIbaValue(holder, ibaName);
        boolean product = getProduct(strName);
        if(product){
            System.out.println(product+"---------------++++++++++++++++"+strName);
            return null;
        }
        PDMLinkProduct pdmlinkProduct = null;
        if(strName==null||strName.trim().length()<=0){
            System.out.println("产品名称不能为空!");
            return null;
        }
        try {
            if (pdmlinkProduct == null) {
                pdmlinkProduct = PDMLinkProduct.newPDMLinkProduct();
                WTPrincipal curUser = SessionHelper.manager.getPrincipal();
                // 当前登录 用户
                if (curUser == null) {
                    System.out.println("用户认证失败,请登陆系统后再使用！");
                    return null;
                }
                WTOrganization wtorganization = curUser.getOrganization();
                //根据用户得 到组织；
                String strContainerTemplate = "常规产品";
                //给定默认的模版
                pdmlinkProduct.setName(strName);
                WTContainerRef containerRef = null;
                //容器
                if (wtorganization != null) {
                    containerRef = WTContainerHelper.service.getOrgContainerRef(wtorganization);               						pdmlinkProduct.setContainerReference(containerRef);
                    pdmlinkProduct.setOrganization(wtorganization);
                }
                if (strContainerTemplate != null && strContainerTemplate.trim().length() > 0) {
                    QuerySpec qs = new QuerySpec(WTContainerTemplateMaster.class);
                    //根据模版名字查询到模版
                    SearchCondition sc = new SearchCondition(WTContainerTemplateMaster.class,
                            WTContainerTemplateMaster.NAME, SearchCondition.EQUAL, strContainerTemplate);
                    qs.appendWhere(sc, new int[] { 0, 1 });
                    LookupSpec lookupspec = new LookupSpec(qs, containerRef);
                    lookupspec.setFirstMatchOnly(true);
                    QueryResult qr = WTContainerHelper.service.lookup(lookupspec);
                    WTContainerTemplateMaster wtcontainertemplatemaster = (WTContainerTemplateMaster) qr.nextElement();
                    System.out.println(" 模 版 类 型 ：    "+wtcontainertemplatemaster.getName()+"   "+wtcontainertemplatemaster.getContainerClassName());
                    WTContainerTemplate containerTemplate = ContainerTemplateHelper.service.getContainerTemplateRef																	(wtcontainertemplatemaster).getTemplate();
                    System.out.println("模版：    "+containerTemplate.getName()+ "   "+ containerTemplate.getContainerClassName());
                    pdmlinkProduct.setContainerTemplate(containerTemplate);
                }
                pdmlinkProduct =(PDMLinkProduct) WTContainerHelper.service.create(pdmlinkProduct);
                System.out.println("产品创建成功！！！！！！！！");
            }
        } catch (WTException e){
            e.printStackTrace();
        } catch (WTPropertyVetoException e){
            e.printStackTrace();
        }
        return pdmlinkProduct;
    }


    /**
     * iba属性获取
     * @param holder
     * @param ibaName
     * @return
     */
    public static String getIbaValue(IBAHolder holder, String ibaName) {
        AttributeDefDefaultView addv;
        try {
            addv = IBADefinitionHelper.service
                    .getAttributeDefDefaultViewByPath(ibaName);
            if (addv == null)
                return null;
            // System.out.println(addv);
            long ibaDefId = addv.getObjectID().getId();

            QuerySpec qs = new QuerySpec(StringValue.class);
            qs.appendWhere(new SearchCondition(StringValue.class,
                            AbstractValue.IBAHOLDER_REFERENCE + "." + ObjectReference.KEY + "."
                                    + ObjectIdentifier.ID, SearchCondition.EQUAL, PersistenceHelper
                            .getObjectIdentifier((Persistable) holder).getId()),
                    new int[] { 0 });
            qs.appendAnd();
            qs.appendWhere(new SearchCondition(StringValue.class,
                            StringValue.DEFINITION_REFERENCE + "." + ObjectReference.KEY + "."
                                    + ObjectIdentifier.ID, SearchCondition.EQUAL, ibaDefId),
                    new int[] { 0 });

            QueryResult qr = PersistenceHelper.manager.find((StatementSpec) qs);
            StringValue sv = null;
            if (qr.hasMoreElements())
                sv = (StringValue) qr.nextElement();
            return sv == null ? null : sv.getValue();
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }
        }
        return null;
    }

    /**
     * 查询产品库
     */
    public static boolean getProduct(String pName) throws WTException {
        QuerySpec qs = new QuerySpec(PDMLinkProduct.class);
        qs.appendWhere(new SearchCondition(PDMLinkProduct.class, PDMLinkProduct.NAME, SearchCondition.EQUAL,
                pName), new int[] { 0 });
        QueryResult qr = PersistenceHelper.manager.find(qs);
        return qr.hasMoreElements();
    }
}
