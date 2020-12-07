package ext.corilead.prod.single.mvc.builders;

import wt.util.resource.RBEntry;
import wt.util.resource.RBUUID;
import wt.util.resource.WTListResourceBundle;

@RBUUID("ext.corilead.prod.single.mvc.builders.WorkflowInfoRB")
public class WorkflowInfoRB extends WTListResourceBundle {

    @RBEntry("一厂")
    public static final String Siyuan = "一厂";

    @RBEntry("二厂")
    public static final String INFORMATIONARCHIVESCENTER  = "二厂";

    @RBEntry("一部")
    public static final String GENERALDEPARTMENT = "一部 ";

    @RBEntry("二部 ")
    public static final String CONTROLOFFICE = "二部";

    @RBEntry("四厂")
    public static final String AEROSPACESCIENCEANDENGINEERING = "四厂";
}
