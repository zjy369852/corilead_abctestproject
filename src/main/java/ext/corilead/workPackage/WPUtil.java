package ext.corilead.workPackage;

import com.ptc.windchill.wp.WPHelper;
import com.ptc.windchill.wp.WorkPackage;
import org.apache.log4j.Logger;
import wt.doc.WTDocument;
import wt.facade.persistedcollection.PersistedCollectionHelper;
import wt.facade.persistedcollection.PersistedCollectionRecipe;
import wt.fc.Persistable;
import wt.fc.collections.WTHashSet;
import wt.inf.container.WTContainer;
import wt.inf.container.WTContainerRef;
import wt.lifecycle.LifeCycleHelper;
import wt.lifecycle.LifeCycleTemplate;
import wt.lifecycle.State;
import wt.part.WTPart;
import wt.pds.ObjectReferenceOidHolder;
import wt.pom.PersistenceException;
import wt.pom.Transaction;
import wt.session.SessionHelper;
import wt.session.SessionServerHelper;
import wt.type.TypeDefinitionReference;
import wt.type.TypedUtility;
import wt.util.WTException;
import wt.util.WTRuntimeException;
import wt.vc.Iterated;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WPUtil {

    private static Logger logger = Logger.getLogger(WPUtil.class);



    /**
     * 创建批量签审包
     * @param part
     * @throws Exception
     */
    public static WorkPackage createPackage(WTPart part) throws Exception {
        boolean flag = SessionServerHelper.manager.setAccessEnforced(false);
        // List objs = new ArrayList();
        try {
            WorkPackage wp = WorkPackage.newWorkPackage();
            WTContainer container = part.getContainer();
            wp.setContainer(container);
            TypeDefinitionReference typed = TypedUtility
                    .getTypeDefinitionReference("com.ptc.windchill.wp.WorkPackage|com.zhzx.PILIANGQIANSHENBAO");
            wp.setTypeDefinitionReference(typed);
            WTContainerRef cref = WTContainerRef.newWTContainerRef(container);


            wp.setName("部件签审："+part.getName() + "(" + part.getNumber() + ")");
            wp = (WorkPackage) WPHelper.service.save(wp);

            return wp;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SessionServerHelper.manager.setAccessEnforced(flag);
        }
        return null;
    }

    public static boolean addPackageMembers(WorkPackage workPackage, Persistable persistable) {
        logger.info("addPackageMembers(WorkPackage workPackage, Persistable persistable)");
        if(persistable instanceof WTPart) {
            logger.info("add a part it's name:"+((WTPart)persistable).getName());
        }
        Transaction transaction = new Transaction();
        try {
            transaction.start();
            PersistedCollectionRecipe pcr = PersistedCollectionHelper.service
                    .getRecipe(workPackage);
            if(pcr == null){
                pcr = PersistedCollectionRecipe.newPersistedCollectionRecipe();
            }
            if (!PersistedCollectionHelper.isCheckedOut(workPackage)) {
                workPackage = (WorkPackage) PersistedCollectionHelper.service
                        .checkout(workPackage);
            }
            PersistedCollectionHelper.service.addSeed(workPackage, persistable);
            PersistedCollectionHelper.service.setRecipe(workPackage, pcr);
            WPHelper.service.save(workPackage);
            WPHelper.service.refresh(workPackage, SessionHelper.getLocale());
            if (!PersistedCollectionHelper.isCheckedIn(workPackage)) {
                workPackage = (WorkPackage) PersistedCollectionHelper.service
                        .checkin(workPackage);
            }
            WPHelper.service.refresh(workPackage, SessionHelper.getLocale());
            transaction.commit();
            transaction = null;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            if (transaction != null) {
                transaction.rollback();
                return false;
            }
            return true;
        }
    }
}
