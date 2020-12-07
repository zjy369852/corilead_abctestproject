package ext.corilead.prod.single.Processor;



import ext.corilead.prod.single.ProductionSingleKind;
import com.ptc.core.components.beans.ObjectBean;
import com.ptc.core.components.forms.DefaultObjectFormProcessor;

import com.ptc.core.components.forms.FormResult;

import com.ptc.netmarkets.model.NmOid;
import com.ptc.netmarkets.util.beans.NmCommandBean;

import wt.util.WTException;



import java.util.List;
import java.util.Map;

public class ProductionCreateProcessor extends DefaultObjectFormProcessor {
    public static String MBA_productionCode ="MdlAttr+java.lang.String+WCTYPE|com.prod.single.ProductionSingle~MBA|productCode~~NEW|-6976853890864445860~+null_col_MdlAttr+java.lang.String+WCTYPE|com.prod.single.ProductionSingle~MBA|productCode~~NEW|-6976853890864445860~+null";
    public static String MBA_telephone = "MdlAttr+java.lang.String+WCTYPE|com.prod.single.ProductionSingle~MBA|telephone~~NEW|-6976853890864445860~+null_col_MdlAttr+java.lang.String+WCTYPE|com.prod.single.ProductionSingle~MBA|telephone~~NEW|-6976853890864445860~+null";
    public static String MBA_remarks = "MdlAttr+java.lang.String+WCTYPE|com.prod.single.ProductionSingle~MBA|remarks~~NEW|-6976853890864445860~+null_col_MdlAttr+java.lang.String+WCTYPE|com.prod.single.ProductionSingle~MBA|remarks~~NEW|-6976853890864445860~+null";
    public static String MBA_kind= "MdlAttr+java.lang.String+WCTYPE|com.prod.single.ProductionSingle~MBA|kind~~NEW|-6976853890864445860~+null_col_MdlAttr+java.lang.String+WCTYPE|com.prod.single.ProductionSingle~MBA|kind~~NEW|-6976853890864445860~+null";
    @SuppressWarnings({"unused","deprecation"})
    @Override
    public FormResult doOperation(NmCommandBean nmcommandbean, List<ObjectBean> list) throws WTException {
        FormResult fr=new FormResult();
        Map map = nmcommandbean.getText();//文本productCode
        Map hashmap = nmcommandbean.getParameterMap();
        List selectlist = nmcommandbean.getSelected();
        for(Object objstr:hashmap.keySet()) {
            String key = (String)objstr;
            System.out.println("hashmap.keySet():key-->"+objstr);
            System.out.println("hashmap.keySet():value-->"+map.get(objstr));

        }
        for(ObjectBean obj:list){
            System.out.println("selectlist:key-->"+obj);
        }
        String telephone = "";//说明
        String productCode = "";
        String remaks = "";
        String kind="";
        for(Object objstr:map.keySet()) {
            String key = (String)objstr;
            System.out.println("map.keySet():key-->"+objstr);
            System.out.println("map.keySet():value-->"+map.get(objstr));
            if(key.indexOf("productCode")>0){
                productCode = (String)map.get(objstr);
            }
            if(key.indexOf("telephone")>0){
                telephone = (String)map.get(objstr);
            }
            if(key.indexOf("remaks")>0){
                remaks = (String)map.get(objstr);
            }
        }
        Map map1 = nmcommandbean.getTextArea();//文本框
        Map map2 = nmcommandbean.getComboBox();//下拉框
        for(Object objstr:map2.keySet()) {
            String key = (String)objstr;
            System.out.println("map2.keySet():key-->"+objstr);
            System.out.println("map2.keySet():value-->"+map2.get(objstr));
            if(key.indexOf("kind")>0){
                kind = (String)map2.get(objstr);
            }
        }
       // String number = (String)map.get("numbers");//标号
        String name = (String)map.get("name_col_name");//名称
//        ProductionSingleKind singleKind = null;//发送类型的内码
//        for(Object str : map2.keySet()){
//            if(((ArrayList)map2.get(str)).get(0) instanceof String){
//                singleKind = (ProductionSingleKind)(((ArrayList)map2.get(str)).get(0));
//            }
//        }

        //ProductionSingleKind kind = (ProductionSingleKind) map1.get(MBA_kind) ;
        //System.out.println("第一页数据：number"+number);
        System.out.println("name"+name);
        System.out.println("telephone"+telephone);
        System.out.println("productCode"+productCode);
        System.out.println("kind"+kind);
//        System.out.println("singleKind"+singleKind);
        List<NmOid> nmOids =  nmcommandbean.getAddedItemsByName("ext.corilead.prod.single.mvc.builders.ProdcutionSingleDataBuilder");//mvctable
        System.out.println("nmOids---"+nmOids.size());
//        System.out.println("第二页数据");
//        int i=1;
//        List<Map<String,Object>> sendObjects =new ArrayList<Map<String,Object>>();//需要发送的对象及发送部门和发送份数放在map里
//        for(NmOid nmOid : nmOids){
//            System.out.println(i+"---nmOid---"+nmOid);//nmoids是发送的文档部件
//            ObjectIdentifier identifier=nmOid.getOidObject();
//            ObjectReference objectRef = ObjectReference.newObjectReference(identifier);
//            ReferenceFactory rf = new ReferenceFactory();
//            String oid=rf.getReferenceString(objectRef);
//            System.out.println("oid----"+oid);
//            String senddepartment=(String)map.get("senddepartment"+oid);//发送部门
//            String sendcopies=(String)map.get("sendcopies"+oid);//发送份数
//            System.out.println("senddepartment:"+senddepartment);
//            System.out.println("sendcopies:"+sendcopies);
//            i++;
//            Map<String,Object> sendobj=new HashMap<String,Object>();
//            sendobj.put("sendobj", nmOid);
//            sendobj.put("senddepartment",senddepartment );
//            sendobj.put("sendcopies",sendcopies );
//            sendObjects.add(sendobj);
//
//        }
//        DataSendOrderBean dsoBean=new DataSendOrderBean();
//        dsoBean.setName(name);
//        dsoBean.setDescribe(describe);
//        dsoBean.setSender(sender);
//        dsoBean.setSendtype(sendtype);
//        dsoBean.setSendObjects(sendObjects);
//
//        try {
//            System.out.println("CREATEDATAORDERFormProcessor-->创建数据发送单对象");
//            DataSendOrderHelper.submitData(dsoBean);
//            System.out.println("CREATEDATAORDERFormProcessor-->创建数据发送单对象成功");
//        }catch(Exception e) {
//            e.printStackTrace();
//            fr.setStatus(FormProcessingStatus.FAILURE);
//            fr.addFeedbackMessage(new FeedbackMessage(FeedbackType.FAILURE, null, null, null, new String[] {"数据发送单创建失败！"}));
//        }
//        fr.setStatus(FormProcessingStatus.SUCCESS);
//        fr.addFeedbackMessage(new FeedbackMessage(FeedbackType.SUCCESS, null, null, null, new String[] {"数据发送单创建成功！"}));
//        //super.doOperation(arg0,arg1);
        return fr;
   }
}
