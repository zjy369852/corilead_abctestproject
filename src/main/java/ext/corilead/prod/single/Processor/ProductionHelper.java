package ext.corilead.prod.single.Processor;

import ext.corilead.prod.single.ProductionSingle;
import ext.corilead.prod.single.ProductionSingleLink;
import com.ptc.netmarkets.model.NmOid;
import wt.fc.*;
import wt.util.WTException;
import wt.util.WTPropertyVetoException;

import java.util.List;
import java.util.Map;

public class ProductionHelper {
    public static ProductionSingle createProduction(ProductionSingle ps, List<Map<String,Object>> proObjects)throws WTException {
        System.out.println("11111");
        for(Map<String,Object> map:proObjects){
            // 创建数据发送单的link对象
            NmOid sendobj=(NmOid)map.get("sendobj");
            ObjectIdentifier identifier=sendobj.getOidObject();
            ObjectReference objectRef = ObjectReference.newObjectReference(identifier);
            WTObject obj=(WTObject) objectRef.getObject();
            String prodnumber = (String)map.get("prodnumber");
            String productionUnit = (String)map.get("productionUnit");
            setProdObjectLink(ps, obj,prodnumber, productionUnit);
            ps.getName();
            ps.getTelephone();
            ps.getProductCode();
            ps.getKind();
            ps.getRemarks();



        }
//        PersistenceHelper.manager.store(ps);
        return ps;
    }

    private static void setProdObjectLink(ProductionSingle ps, WTObject obj, String prodnumber, String productionUnit)throws WTException  {
        ProductionSingleLink linkObj = ProductionSingleLink.newSendObjectLink(ps, obj);
        try {
            linkObj.setProductionUnit(productionUnit);
            linkObj.setNum(Integer.valueOf(prodnumber));
        } catch (WTPropertyVetoException e) {
            e.printStackTrace();
        }
        PersistenceHelper.manager.store(linkObj);
    }
}
