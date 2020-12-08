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
     * ������Ʒ��
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
            System.out.println("��Ʒ���Ʋ���Ϊ��!");
            return null;
        }
        try {
            if (pdmlinkProduct == null) {
                pdmlinkProduct = PDMLinkProduct.newPDMLinkProduct();
                WTPrincipal curUser = SessionHelper.manager.getPrincipal();
                // ��ǰ��¼ �û�
                if (curUser == null) {
                    System.out.println("�û���֤ʧ��,���½ϵͳ����ʹ�ã�");
                    return null;
                }
                //�����û��� ����֯��
                WTOrganization wtorganization = curUser.getOrganization();
                String strContainerTemplate = "�����Ʒ";
                //����Ĭ�ϵ�ģ��
                pdmlinkProduct.setName(strName);
                WTContainerRef containerRef = null;
                //����
                if (wtorganization != null) {
                    containerRef = WTContainerHelper.service.getOrgContainerRef(wtorganization);
                    pdmlinkProduct.setContainerReference(containerRef);
                    pdmlinkProduct.setOrganization(wtorganization);
                }
                if (strContainerTemplate != null && strContainerTemplate.trim().length() > 0) {
                    QuerySpec qs = new QuerySpec(WTContainerTemplateMaster.class);
                    //����ģ�����ֲ�ѯ��ģ��
                    SearchCondition sc = new SearchCondition(WTContainerTemplateMaster.class,
                            WTContainerTemplateMaster.NAME, SearchCondition.EQUAL, strContainerTemplate);
                    qs.appendWhere(sc, new int[] { 0, 1 });
                    LookupSpec lookupspec = new LookupSpec(qs, containerRef);
                    lookupspec.setFirstMatchOnly(true);
                    QueryResult qr = WTContainerHelper.service.lookup(lookupspec);
                    WTContainerTemplateMaster wtcontainertemplatemaster = (WTContainerTemplateMaster) qr.nextElement();
                    System.out.println(" ģ �� �� �� ��    "+wtcontainertemplatemaster.getName()+"   "+wtcontainertemplatemaster.getContainerClassName());
                    WTContainerTemplate containerTemplate = ContainerTemplateHelper.service.getContainerTemplateRef																	(wtcontainertemplatemaster).getTemplate();
                    System.out.println("ģ�棺    "+containerTemplate.getName()+ "   "+ containerTemplate.getContainerClassName());
                    pdmlinkProduct.setContainerTemplate(containerTemplate);
                }
                pdmlinkProduct =(PDMLinkProduct) WTContainerHelper.service.create(pdmlinkProduct);
                System.out.println("��Ʒ�����ɹ�����������������");
            }
        } catch (WTException e){
            e.printStackTrace();
        } catch (WTPropertyVetoException e){
            e.printStackTrace();
        }
        return pdmlinkProduct;
    }


    /**
     * iba���Ի�ȡ
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
     * ��ѯ��Ʒ��
     */
    public static boolean getProduct(String pName) throws WTException {
        QuerySpec qs = new QuerySpec(PDMLinkProduct.class);
        qs.appendWhere(new SearchCondition(PDMLinkProduct.class, PDMLinkProduct.NAME, SearchCondition.EQUAL,
                pName), new int[] { 0 });
        QueryResult qr = PersistenceHelper.manager.find(qs);
        return qr.hasMoreElements();
    }
}
