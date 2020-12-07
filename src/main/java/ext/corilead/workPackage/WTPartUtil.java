package ext.corilead.workPackage;

import com.ptc.windchill.wp.WPHelper;
import com.ptc.windchill.wp.WorkPackage;
import wt.fc.Persistable;
import wt.fc.WTObject;
import wt.fc.collections.WTHashSet;
import wt.lifecycle.LifeCycleHelper;
import wt.lifecycle.LifeCycleManaged;
import wt.lifecycle.State;
import wt.part.WTPart;
import wt.pds.ObjectReferenceOidHolder;
import wt.util.WTException;
import wt.util.WTRuntimeException;
import wt.vc.Iterated;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WTPartUtil {

    public static void modifyState(WTObject obj, String stateName) throws WTException {
        State state = State.toState(stateName);
        try {
            LifeCycleHelper.service.setLifeCycleState((LifeCycleManaged) obj, state);

            WorkPackage wp=(WorkPackage)obj;
            getAllMembers(wp,state);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static List<Persistable> getAllMembers(WorkPackage wp,State state){
        List<Persistable> list = new ArrayList();
        try {
            WTHashSet allMembers = (WTHashSet) WPHelper.service.getAllMembers(wp);

            Iterator allMembersIterator = allMembers.iterator();
            while (allMembersIterator.hasNext()) {
                Persistable member = (Persistable) ((ObjectReferenceOidHolder) allMembersIterator.next()).getObject();
                if (member instanceof Iterated) {
                    if(member instanceof WTPart) {
                        LifeCycleHelper.service.setLifeCycleState((WTPart) member, state);
                        list.add(member);
                    }
                }
            }
        } catch (WTRuntimeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WTException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}
