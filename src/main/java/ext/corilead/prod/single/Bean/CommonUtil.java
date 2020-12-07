package ext.corilead.prod.single.Bean;

import ext.corilead.prod.single.ProductionSingle;
import com.ptc.netmarkets.util.misc.NmActionServiceHelper;
import wt.fc.Persistable;
import wt.fc.PersistenceHelper;
import wt.fc.ReferenceFactory;
import wt.fc.WTReference;
import wt.fc.collections.WTCollection;
import wt.folder.Folder;
import wt.httpgw.URLFactory;
import wt.util.WTException;

import wt.util.WTPropertyVetoException;
import wt.vc.Iterated;
import wt.vc.VersionControlHelper;

import wt.vc.wip.WorkInProgressHelper;


import java.util.HashMap;
import java.util.Map;


import wt.vc.wip.Workable;


    public class CommonUtil {

        public Map commonImport(String queueid, String uuid, String commonType) {
            Map dataMap = new HashMap();
            try{
                String activityresult = "";
                String errorDesc = "";

                // key 值从配置文件活动中读取
                // dataMap.put("activityname","");
                dataMap.put("activityresult",activityresult);
                // dataMap.put("activitydate","");
                // 错误描述
                dataMap.put("errorDesc",errorDesc);
                // dataMap.put("orgid","receiveDepartId");
                // dataMap.put("destorgid","sendDepartId");
                // dataMap.put("srcQueueId","");
                // <!-- commonType to pboname -->
                dataMap.put("pboname",commonType);


            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return dataMap;
        }


        /**
         * @author Zheng Yongliang
         * @param args
         */
        public static void main(String[] args) {
            // TODO Auto-generated method stub

        }
        /**
         * 获取可持续对象详情页的url
         * @param persistable
         * @return
         */
        public static String getInfoPageURL(Persistable persistable) {
            ReferenceFactory rf= new ReferenceFactory();
            String oid=null;
            URLFactory uf=null;
            HashMap<String,String> map=new HashMap<String,String>();
            String action="";
            try {
                oid=rf.getReferenceString(persistable);
                map.put("oid", oid);
                uf=new URLFactory();
                action = NmActionServiceHelper.service.getAction("object", "view").getUrl();
            } catch (WTException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String infourl=uf.getHREF(action, map, true);
            return infourl;
        }
        /**
         * 获取对象
         *
         * @param oid
         *
         * @return
         */
        public static Persistable getPersistable(String oid) {
            if (oid==null||oid=="") {
                return null;
            }
            try {
                ReferenceFactory ref = new ReferenceFactory();
                WTReference wtref = ref.getReference(oid);
                if (wtref != null)
                    return wtref.getObject();
            } catch (WTException e) {
                e.printStackTrace();
            }
            return null;
        }




}
