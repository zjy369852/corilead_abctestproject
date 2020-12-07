package ext.corilead.workPackage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import com.ptc.core.components.forms.FormProcessingStatus;
import com.ptc.core.components.forms.FormResult;
import com.ptc.core.components.forms.FormResultAction;
import com.ptc.core.components.util.FeedbackMessage;
import com.ptc.core.ui.resources.FeedbackType;
import com.ptc.netmarkets.util.beans.NmCommandBean;
import com.ptc.windchill.wp.WorkPackage;
import wt.facade.persistedcollection.PersistedCollectionHelper;
import wt.fc.Persistable;
import wt.fc.ReferenceFactory;
import wt.fc.WTObject;
import wt.lifecycle.LifeCycleHelper;
import wt.lifecycle.LifeCycleManaged;
import wt.lifecycle.State;
import wt.method.RemoteMethodServer;
import wt.org.WTPrincipal;
import wt.part.WTPart;
import wt.session.SessionContext;
import wt.session.SessionHelper;
import wt.session.SessionServerHelper;
import wt.vc.VersionReference;
import wt.vc.baseline.ManagedBaseline;
import wt.workflow.engine.WfProcess;

public class TrawUtil {

    /**
     * 解冻申请
     *
     * @param nmcommandbean
     *            上下文环境
     * @return 执行结果
     * @throws Exception
     * @throws IOException
     */
    public static FormResult doSetState(NmCommandBean nmcommandbean) throws Exception {
        if (!RemoteMethodServer.ServerFlag) {
            String method = "doSetState";
            String klass = TrawUtil.class.getName();
            Class[] types = { NmCommandBean.class };
            Object[] values = { nmcommandbean };
            try {
                return (FormResult) RemoteMethodServer.getDefault().invoke(method, klass, null, types, values);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        WTPrincipal user = SessionHelper.getPrincipal();
        SessionContext.setEffectivePrincipal(SessionHelper.manager.getAdministrator());
        FormResult formresult = new FormResult();
        formresult.setNextAction(FormResultAction.NONE);

        Persistable primobject = nmcommandbean.getActionOid().getWtRef().getObject();
       // logger.debug("primobject.getClass()"+primobject.getClass());
        if (!(primobject instanceof LifeCycleManaged))	{
            formresult.addFeedbackMessage(new FeedbackMessage(FeedbackType.FAILURE, SessionHelper.getLocale(), null,
                    null, new String[] { "该对象不能执行解冻申请！" }));
        }

        String state_str = "";
        if (!(primobject instanceof LifeCycleManaged))	{
            formresult.addFeedbackMessage(new FeedbackMessage(FeedbackType.FAILURE, SessionHelper.getLocale(), null,
                    null, new String[] { "该对象不能执行解冻申请！" }));
        } else {
            state_str = ((LifeCycleManaged)primobject).getLifeCycleState().toString();
        }

        //logger.debug("当前状态"+state_str);

        if (!("FREEZE".equalsIgnoreCase(state_str)))	{
            formresult.addFeedbackMessage(new FeedbackMessage(FeedbackType.FAILURE, SessionHelper.getLocale(), null,
                    null, new String[] { "只允许对冻结状态的基线执行解冻申请操作！" }));
        } else {
            try {
                LifeCycleHelper.service.setLifeCycleState((LifeCycleManaged) primobject, State.toState("XIUGAI"),
                        true);
                formresult.addFeedbackMessage(new FeedbackMessage(FeedbackType.SUCCESS, SessionHelper.getLocale(), null,
                        null, new String[] { "解冻申请成功！" }));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                formresult.addFeedbackMessage(new FeedbackMessage(FeedbackType.FAILURE, SessionHelper.getLocale(), null,
                        null, new String[] { "解冻申请失败！" + e.getMessage() }));
            }
        }
        return formresult;
    }

}

	