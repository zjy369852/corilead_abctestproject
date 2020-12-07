package ext.corilead.resource.abcprod;

import wt.util.resource.RBEntry;
import wt.util.resource.RBUUID;
import wt.util.resource.WTListResourceBundle;

@RBUUID("ext.corilead.resource.abcprod.ActionLableResourceRB")
public class ActionLableResourceRB_zh_CN extends WTListResourceBundle {
    @RBEntry("基线提交审核")
    public static final String navtitle = "object.submitReview.title";

    @RBEntry("基线提交审核")
    public static final String navdesc = "object.submitReview.description";

    @RBEntry("基线提交审核")
    public static final String navtool= "object.submitReview.tooltip";

    @RBEntry("基线解冻申请")
    public static final String navtitle1 = "object.applyTraw.title";

    @RBEntry("基线解冻申请")
    public static final String navdesc1 = "object.applyTraw.description";

    @RBEntry("基线解冻申请")
    public static final String navtool1= "object.applyTraw.tooltip";

    @RBEntry("你确认将该对象提交签审流程吗？")
    public static final String  CONFORM_SUBMIT_REVIEW="1";

    @RBEntry("您确定将本对象进行解冻申请流程吗？")
    public static final String  CONFORM_APPLY_THAW="2";

}
