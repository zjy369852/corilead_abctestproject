
package ext.corilead.prod.single.mvc.builders;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wt.clients.beans.query.WTBusinessInfo;
import wt.fc.EnumeratedType;
import wt.htmlutil.HtmlUtil;
import wt.httpgw.GatewayURL;
import wt.introspection.ClassInfo;
import wt.introspection.PropertyDisplayName;
import wt.introspection.WTIntrospectionException;
import wt.introspection.WTIntrospector;
import wt.lifecycle.LifeCycleManaged;
import wt.lifecycle.State;
import wt.method.RemoteAccess;
import wt.method.RemoteMethodServer;
import wt.query.SearchAttributeList;
import wt.query.SearchAttributeListDelegate;
import wt.query.SearchAttributeListFactory;
import wt.query.SearchTask;
import wt.templateutil.components.HTMLComponentFactory;
import wt.templateutil.processor.GenerateFormProcessor;
import wt.templateutil.table.DefaultHTMLTableColumnModel;
import wt.templateutil.table.RowDataTableModel;
import wt.templateutil.table.WTHtmlTable;
import wt.util.SortedEnumeration;
import wt.util.WTException;
import wt.util.WTMessage;
import wt.util.WTPropertyVetoException;

public class DataSendOrderSearchProcessor extends GenerateFormProcessor implements RemoteAccess, Serializable {
    private static final String CLASSNAME = DataSendOrderSearchProcessor.class.getName();
    static final long serialVersionUID = 1L;
    public static final long EXTERNALIZATION_VERSION_UID = 6672846455623631057L;
    protected static final long OLD_FORMAT_VERSION_UID = 5973475918382829810L;
    protected SortedEnumeration results;
    public String query;
    protected String[] hiddenFields = new String[]{"linkclass"};
    protected String[] otherFields = new String[]{"query", "action", "submit", "oid"};
    public static boolean VERBOSE = false;
    public static final String STATENAME = "0";
    public static final String STATEVALUE = "1";
    private static final Logger logger = LoggerFactory.getLogger(DataSendOrderSearchProcessor.class);

    public void writeExternal(ObjectOutput output) throws IOException {
        output.writeLong(6672846455623631057L);
        super.writeExternal(output);
    }

    public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {
        long readSerialVersionUID = input.readLong();
        this.readVersion(this, input, readSerialVersionUID, false, false);
    }

    protected boolean readVersion(DataSendOrderSearchProcessor thisObject, ObjectInput input, long readSerialVersionUID, boolean passThrough, boolean superDone) throws IOException, ClassNotFoundException {
        boolean success = true;
        if (readSerialVersionUID == 6672846455623631057L) {
            if (!superDone) {
                super.readExternal(input);
            }
        } else {
            success = this.readOldVersion(input, readSerialVersionUID, passThrough, superDone);
        }

        return success;
    }

    private boolean readOldVersion(ObjectInput input, long readSerialVersionUID, boolean passThrough, boolean superDone) throws IOException, ClassNotFoundException {
        boolean success = true;
        if (readSerialVersionUID != 5973475918382829810L) {
            if (superDone) {
                throw new InvalidClassException(CLASSNAME, "Local class not compatible: stream classdesc externalizationVersionUID=" + readSerialVersionUID + " local class externalizationVersionUID=" + 6672846455623631057L);
            }

            success = super.readVersion(this, input, readSerialVersionUID, false, false);
            if (success && !passThrough && readSerialVersionUID != 6662754576076473523L) {
                this.readVersion(this, input, input.readLong(), false, true);
            }
        }

        return success;
    }

    public void doObjectSearch(Properties properties, Locale locale, OutputStream outputstream) throws WTException, WTPropertyVetoException {
        if (VERBOSE) {
            logger.debug("Inside doObjectSearch");
        }

        String s = null;
        if (this.getFormData() != null) {
            s = this.getFormData().getProperty("QUERY");
        }

        if ((s == null || s.length() == 0 || s.equals("")) && this.getQueryData() != null) {
            s = this.getQueryData().getProperty("QUERY");
        }

        if (s != null && s.length() != 0 && !s.equals("")) {
            if (VERBOSE) {
                logger.debug("QUERY QUERY:" + s);
            }

            this.setQuery(s);
            PrintWriter printwriter = this.getPrintWriter(outputstream, locale);
            SearchAttributeListDelegate searchattributelistdelegate = SearchAttributeListFactory.getSearchAttributeListDelegate(locale);
            Vector vector = null;
            Vector vector1 = null;
            Object obj = null;
            Object var10 = null;

            try {
                int i = searchattributelistdelegate.getQueryType(this.getQuery());
                vector1 = searchattributelistdelegate.getQueryClass(i);
                vector = searchattributelistdelegate.getOutputAttributes(i);
                SearchAttributeList.getPropertyDescriptors(vector, vector1);
                searchattributelistdelegate.getOutputProcessing(i);
            } catch (WTException var15) {
                String s1 = WTMessage.getLocalizedMessage("wt.change2.htmlclient.htmlclientResource", "28", (Object[])null, locale);
                printwriter.println(s1);
                printwriter.flush();
                return;
            }

            Hashtable hashtable = new Hashtable();
            if (VERBOSE) {
                logger.debug("Inside doObjectSearch 2");
            }

            Enumeration enumeration = this.getFormData().keys();

            String s5;
            String s4;
            while(enumeration.hasMoreElements()) {
                s5 = (String)enumeration.nextElement();
                s4 = this.getFormData().getProperty(s5);
                if (!s5.equals(s5.toUpperCase()) && this.notOtherFields(s5) && this.notHiddenFields(s5) && !s4.equals("") && s4 != null) {
                    hashtable.put(s5, s4);
                }
            }

            if (VERBOSE) {
                logger.debug("Inside doObjectSearch 3");
            }

            SearchTask searchtask = new SearchTask();

            try {
                searchtask.setSearchFilter("ALL_VERSIONS");
                this.results = searchtask.search(vector1, hashtable, vector, (String)null, locale);
            } catch (WTPropertyVetoException var16) {
                var16.printStackTrace();
                s4 = WTMessage.getLocalizedMessage("wt.query.queryResource", "28", (Object[])null, locale);
                System.err.println(s4);
            } catch (WTException var17) {
                var17.printStackTrace();
                if (VERBOSE) {
                    logger.debug("Inside doObjectSearch 4");
                }

                s5 = WTMessage.getLocalizedMessage("wt.query.queryResource", "28", (Object[])null, locale);
                System.err.println(s5);
            }
        } else {
            throw new WTException("You must specify the query in the either the URL or the Form Data");
        }
    }

    public void showObjectSearch(Properties properties, Locale locale, OutputStream outputstream) throws WTException, WTPropertyVetoException {
        this.showDocumentObjectSearch(properties, locale, outputstream);
    }

    public void showUsesObjectSearch(Properties properties, Locale locale, OutputStream outputstream) throws WTException, WTPropertyVetoException {
        if (VERBOSE) {
            logger.debug("Inside showUsesObjectSearch");
        }

        Vector vector = new Vector();

        while(this.results.hasMoreElements()) {
            vector.addElement(this.results.nextElement());
        }

        String s = null;
        if (this.getFormData() != null) {
            s = this.getFormData().getProperty("QUERY");
        }

        if ((s == null || s.length() == 0 || s.equals("")) && this.getQueryData() != null) {
            s = this.getQueryData().getProperty("QUERY");
        }

        if (s != null && s.length() != 0 && !s.equals("")) {
            if (VERBOSE) {
                logger.debug("Inside showObjectSearch 2");
            }

            this.setQuery(s);
            SearchAttributeListDelegate searchattributelistdelegate = SearchAttributeListFactory.getSearchAttributeListDelegate(locale);
            int i = searchattributelistdelegate.getQueryType(this.getQuery());
            Vector vector1 = searchattributelistdelegate.getOutputAttributes(i);
            WTHtmlTable table = new WTHtmlTable();
            RowDataTableModel rowdatatablemodel = new RowDataTableModel();
            rowdatatablemodel.setRowDataObjects(vector);
            rowdatatablemodel.setTableColumns(vector1);
            rowdatatablemodel.setLocale(locale);
            table.setLocale(locale);
            table.setPresentCheckBox(true);
            table.setTableModel(rowdatatablemodel);
            table.setTableColumnModel(new DefaultHTMLTableColumnModel());
            table.createDefaultColumnsFromModel();
            table.setOutputStream(outputstream);
            if (VERBOSE) {
                logger.debug("Inside showObjectSearch 3");
            }

            table.init((String)null, (Object)null, (HTMLComponentFactory)null, (String)null, (Properties)null);
            this.getHTMLTableService().setHtmlTable(table);
        } else {
            throw new WTException("You must specify the query in the either the UTR or the Form Data");
        }
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String s) throws WTPropertyVetoException {
        this.queryValidate(s);
        this.query = s;
    }

    public void listClasses(Properties properties, Locale locale, OutputStream outputstream) throws WTException {
        if (VERBOSE) {
            logger.debug("Inside listClasses");
        }

        String s = "";
        String s1 = "wt.query.queryResource";
        String s2 = null;
        if (this.getFormData() != null) {
            s2 = this.getFormData().getProperty("query");
        }

        if ((s2 == null || s2.length() == 0 || s2.equals("")) && this.getQueryData() != null) {
            s2 = this.getQueryData().getProperty("query");
        }

        String sendType = null;
        if (this.getFormData() != null) {
            sendType = this.getFormData().getProperty("sendtype");
        }

        List classes = new ArrayList();

        for(int i = 0; i < classes.size(); ++i) {
            String s4 = (String)classes.get(i);
            String s6 = WTMessage.getLocalizedMessage(s1, s4, (Object[])null, locale);
            String s7 = "VALUE=\"" + s4 + "\"";
            if (s2 != null && s2.equals(s4)) {
                s7 = s7.concat(" SELECTED");
            }

            s = s.concat(HtmlUtil.addFormSelectOption(s7, s6));
        }

        String s5 = "name = \"";
        s5 = s5.concat("QUERY");
        s5 = s5.concat("\"");
        s5 = s5.concat(" onChange=\"goToSearch(this)\"");
        PrintWriter printwriter = this.getPrintWriter(outputstream, locale);
        printwriter.println(HtmlUtil.addFormSelect(s5, s));
        printwriter.println("<SCRIPT LANGUAGE=\"JavaScript1.1\">");
        printwriter.println("<!--");
        printwriter.println("function goToSearch(pick) {");
        printwriter.println("   var selection;");
        printwriter.println("   var found = 0;");
        printwriter.println("   for (var i = 0; i < pick.options.length && found == 0; i++) {");
        printwriter.println("      if (pick.options[i].selected) {");
        printwriter.println("         selection = pick.options[i].value;");
        printwriter.println("         found = 1;");
        printwriter.println("      }");
        printwriter.println("   }");
        printwriter.println("   if (found == 1) {");
        printwriter.println("      var s = \"?action=AddSendDataObject&query=\" + selection+ \"&sendtype=" + sendType + "\";");
        printwriter.println("      location.search=s;");
        printwriter.println("   }");
        printwriter.println("}");
        printwriter.println("//end hiding -->");
        printwriter.println("</SCRIPT>");
        printwriter.flush();
    }

    public void generateSearchForm(Properties properties, Locale locale, OutputStream outputstream) throws WTIntrospectionException, WTException, WTPropertyVetoException {
        if (VERBOSE) {
            logger.debug("Inside generateSearchForm");
        }

        String s = null;
        if (this.getFormData() != null) {
            s = this.getFormData().getProperty("QUERY");
        }

        if ((s == null || s.length() == 0 || s.equals("")) && this.getQueryData() != null) {
            s = this.getQueryData().getProperty("QUERY");
        }

        if (s != null && s.length() != 0 && !s.equals("")) {
            this.setQuery(s);
            String sendType = null;

            try {
                if (this.getFormData() != null) {
                    sendType = this.getFormData().getProperty("sendtype");
                }
            } catch (Exception var23) {
                var23.printStackTrace();
            }

            if (sendType == null) {
                sendType = "0";
            }

            PrintWriter printwriter = this.getPrintWriter(outputstream, locale);
            SearchAttributeListDelegate searchattributelistdelegate = SearchAttributeListFactory.getSearchAttributeListDelegate(locale);
            String s1 = this.getQuery();

            Vector vector;
            Vector vector1;
            Vector vector4;
            Class class1;
            String s3;
            try {
                Vector vector2 = searchattributelistdelegate.getPickList();
                Vector vector3 = searchattributelistdelegate.getPickValues();
                int i = searchattributelistdelegate.getQueryType(s1);
                Vector vector5 = searchattributelistdelegate.getQueryClass(i);
                class1 = (Class)vector5.elementAt(0);
                vector = searchattributelistdelegate.getInputAttributes(i);
                vector1 = searchattributelistdelegate.getInputProcessing(i);
                vector4 = SearchAttributeList.getPropertyDescriptors(vector, class1);
            } catch (WTException var22) {
                s3 = WTMessage.getLocalizedMessage("wt.query.queryResource", "28", (Object[])null, locale);
                printwriter.println(s3);
                printwriter.println("</FORM>");
                printwriter.flush();
                this.handleExceptionTP("localSearch", var22, true, properties, locale, outputstream);
                return;
            }

            printwriter.println("<TABLE ALIGN=LEFT BORDER=0 CELLPADDING=0 CELLSPACING=0 WIDTH=90%> <TR><TD>");
            printwriter.println("</TD></TR><TR><TD>&nbsp;</TD></TR><TR><TD>&nbsp;</TD></TR>");
            printwriter.println("<TR><TD>");
            printwriter.println("<TABLE BGCOLOR=\"ffffff\" BORDER=\"0\" CELLPADDING=\"1\">");
            WTIntrospector.getClassInfo(class1);
            if (VERBOSE) {
                logger.debug("Inside generateSearchForm 2");
            }

            for(int j = 0; j < vector.size(); ++j) {
                s3 = (String)vector.elementAt(j);
                PropertyDescriptor propertydescriptor = (PropertyDescriptor)vector4.elementAt(j);
                String s8;
                if (propertydescriptor == null) {
                    Object[] aobj = new Object[]{s3, class1};
                    s8 = WTMessage.getLocalizedMessage("wt.query.queryResource", "2", aobj);
                    printwriter.println("<TR>");
                    printwriter.println("<TD ALIGN=RIGHT BGCOLOR=\"#E3E3CF\">");
                    printwriter.println("</TD>");
                    printwriter.println("<TD ALIGN=LEFT BGCOLOR=\"#E3E3CF\">");
                    printwriter.println("<B>" + s8);
                    printwriter.println("</TD>");
                    printwriter.println("</TR>");
                } else {
                    String s5 = ClassInfo.getPropertyDisplayName(propertydescriptor, locale);
                    printwriter.println("<TR>");
                    printwriter.println("<TD ALIGN=RIGHT BGCOLOR=\"#E3E3CF\">");
                    printwriter.println("<B>" + s5 + ":");
                    printwriter.println("</TD>");
                    printwriter.println("<TD ALIGN=LEFT BGCOLOR=\"#E3E3CF\">");
                    s8 = (String)vector1.elementAt(j);
                    Vector vector6 = this.getValueList(propertydescriptor, locale);
                    if (s3.equalsIgnoreCase("lifeCycleState")) {
                        vector6 = this.getSendState(sendType, "0");
                    }

                    int l;
                    String s10;
                    if (vector6.size() > 0) {
                        printwriter.println("<SELECT NAME=\"" + s3 + "\">");
                        printwriter.println("<OPTION VALUE=\"\">");
                        String selected = "";
                        if (vector6.size() == 1) {
                            selected = " SELECTED";
                        }

                        for(l = 0; l < vector6.size(); ++l) {
                            s10 = (String)vector6.elementAt(l);
                            printwriter.println("<OPTION VALUE='" + s10 + "'" + selected + ">" + s10);
                        }

                        printwriter.println("</SELECT>");
                    } else if (!s8.equals("0")) {
                        try {
                            Vector vector7 = this.getLegalValueList(s8);
                            printwriter.println("<SELECT NAME=\"" + s3 + "\">");
                            printwriter.println("<OPTION VALUE=\"\">");

                            for(l = 0; l < vector7.size(); ++l) {
                                s10 = (String)vector7.elementAt(l);
                                printwriter.println("<OPTION VALUE=\"" + s10 + "\">" + s10);
                            }

                            printwriter.println("</SELECT>");
                        } catch (WTException var24) {
                            printwriter.println("<INPUT NAME = \"" + s3 + "\" TYPE=\"text\">");
                        }
                    } else {
                        printwriter.println("<INPUT NAME = \"" + s3 + "\" TYPE=\"text\">");
                    }

                    printwriter.println("</TD>");
                    printwriter.println("</TR>");
                }
            }

            if (VERBOSE) {
                logger.debug("Inside generateSearchForm 3");
            }

            printwriter.println("</TABLE>");
            printwriter.println("</TD></TR>");
            printwriter.println("<P>");
            printwriter.println("<TR><TD>");
            printwriter.println("<TABLE ALIGN=LEFT><TR><TD>");
            String s4 = WTMessage.getLocalizedMessage("wt.query.queryResource", "33", (Object[])null, locale);
            printwriter.println("<P>");
            printwriter.println("<INPUT TYPE=\"submit\" NAME=\"submit\" VALUE=\"" + s4 + "\"" + "id=button>");
            printwriter.println("</INPUT>");
            printwriter.println("</TD></TR>");
            printwriter.println("</TABLE>");
            printwriter.println("</TD></TR>");
            printwriter.println("</TABLE>");
            printwriter.println("<INPUT TYPE=\"hidden\" name=\"query\" value=\"" + this.getQuery() + "\">");
            printwriter.flush();
        } else {
            throw new WTException("You must specify the query in the either the UTR or the Form Data");
        }
    }

    public void generateFormTag(Properties properties, Locale locale, OutputStream outputstream) throws WTException {
        if (VERBOSE) {
            logger.debug("inside generateformTag");
        }

        PrintWriter printwriter = this.getPrintWriter(outputstream, locale);

        try {
            new Properties();
            Properties properties1 = this.getQueryData();
            properties1.put("action", "FindSendDataOrderResult");
            logger.debug("generateformTag:Properties " + properties1);
            Class class1 = Class.forName("wt.enterprise.URLProcessor");
            URL url = GatewayURL.buildAuthenticatedURL(class1.getName(), "URLTemplateAction", properties1);
            printwriter.println("<FORM ACTION=\"" + url.toString() + "\" METHOD=\"POST\">");
            printwriter.flush();
        } catch (Exception var8) {
            throw new WTException(var8);
        }
    }

    public void currentSearchString(Properties properties, Locale locale, OutputStream outputstream) {
        logger.debug("Inside cSearchString");
        PrintWriter printwriter = this.getPrintWriter(outputstream, locale);
        Properties properties2 = this.getState().getFormData();
        String currentSearchString = "";
        if (properties2 != null) {
            currentSearchString = properties2.getProperty("TextSearch");
        }

        if (currentSearchString != null && currentSearchString != "") {
            printwriter.print(currentSearchString);
        }

        printwriter.flush();
    }

    public void generateHiddenActionControl(Properties properties, Locale locale, OutputStream outputstream) {
        if (VERBOSE) {
            logger.debug("Inside generateHiddenActionControl");
        }

        String s = "<INPUT type=hidden name=\"action\" value=\"";
        String currentAction = this.getQueryData().getProperty("CurrentAction");
        if (currentAction != null) {
            s = s + currentAction + "\">";
        } else {
            logger.debug("CurrentAction is NULL");
        }

        PrintWriter printwriter = this.getPrintWriter(outputstream, locale);
        printwriter.println(s);
        printwriter.flush();
    }

    public DataSendOrderSearchProcessor() {
    }

    public void showDocumentObjectSearch(Properties properties, Locale locale, OutputStream outputstream) throws WTException, WTPropertyVetoException {
        if (VERBOSE) {
            logger.debug("Inside showObjectSearch");
        }

        Vector vector = new Vector();

        while(this.results.hasMoreElements()) {
            vector.addElement(this.results.nextElement());
        }

        String s = null;
        if (this.getFormData() != null) {
            s = this.getFormData().getProperty("QUERY");
        }

        if ((s == null || s.length() == 0 || s.equals("")) && this.getQueryData() != null) {
            s = this.getQueryData().getProperty("QUERY");
        }

        if (s != null && s.length() != 0 && !s.equals("")) {
            if (VERBOSE) {
                logger.debug("Inside showObjectSearch 2");
            }

            this.setQuery(s);
            SearchAttributeListDelegate searchattributelistdelegate = SearchAttributeListFactory.getSearchAttributeListDelegate(locale);
            int i = searchattributelistdelegate.getQueryType(this.getQuery());
            Vector vector1 = searchattributelistdelegate.getOutputAttributes(i);
            WTHtmlTable wthtmltable = new WTHtmlTable();
            wthtmltable.setPresentCheckBox(true);
            RowDataTableModel rowdatatablemodel = new RowDataTableModel();
            rowdatatablemodel.setRowDataObjects(vector);
            rowdatatablemodel.setTableColumns(vector1);
            rowdatatablemodel.setLocale(locale);
            wthtmltable.setLocale(locale);
            wthtmltable.setTableModel(rowdatatablemodel);
            wthtmltable.setTableColumnModel(new DefaultHTMLTableColumnModel());
            wthtmltable.createDefaultColumnsFromModel();
            wthtmltable.setOutputStream(outputstream);
            if (VERBOSE) {
                logger.debug("Inside showObjectSearch 3");
            }

            wthtmltable.init((String)null, (Object)null, (HTMLComponentFactory)null, (String)null, (Properties)null);
            this.getHTMLTableService().setHtmlTable(wthtmltable);
        } else {
            throw new WTException("You must specify the query in the either the UTR or the Form Data");
        }
    }

    protected boolean notOtherFields(String s) {
        int i = this.otherFields.length;

        for(int j = 0; j < i; ++j) {
            if (s.equals(this.otherFields[j])) {
                return false;
            }
        }

        return true;
    }

    protected boolean notHiddenFields(String s) {
        int i = this.hiddenFields.length;

        for(int j = 0; j < i; ++j) {
            if (s.equals(this.hiddenFields[j])) {
                return false;
            }
        }

        return true;
    }

    private void queryValidate(String s) throws WTPropertyVetoException {
        if (s != null && s.length() > 200) {
            Object[] aobj = new Object[]{new PropertyDisplayName(CLASSNAME, "query"), "200"};
            throw new WTPropertyVetoException("wt.fc.fcResource", "20", aobj, new PropertyChangeEvent(this, "query", this.query, s));
        }
    }

    private Vector getLegalValueList(String s) throws WTException {
        int i = s.lastIndexOf(46);
        String s1 = s.substring(0, i);
        String s2 = s.substring(i + 1);

        try {
            Method method = Class.forName(s1).getMethod(s2, (Class[])null);
            Vector vector = (Vector)method.invoke((Object)null, (Object[])null);
            return vector;
        } catch (InvocationTargetException var7) {
            throw new WTException(var7);
        } catch (IllegalAccessException var8) {
            throw new WTException(var8);
        } catch (NoSuchMethodException var9) {
            throw new WTException(var9);
        } catch (ClassNotFoundException var10) {
            throw new WTException(var10);
        }
    }

    private Vector getValueList(PropertyDescriptor propertydescriptor, Locale locale) {
        Vector vector = new Vector();
        Class class1 = propertydescriptor.getPropertyType();
        if (class1 == WTBusinessInfo.BoolTYPE) {
            vector.addElement(WTMessage.getLocalizedMessage("wt.clients.beans.query.BeansQueryRB", "17", (Object[])null, locale));
            vector.addElement(WTMessage.getLocalizedMessage("wt.clients.beans.query.BeansQueryRB", "16", (Object[])null, locale));
        } else if (SearchTask.isEnumType(class1)) {
            EnumeratedType[] aenumeratedtype = WTBusinessInfo.getAttributeValueSet(propertydescriptor);
            if (aenumeratedtype.length != 0) {
                for(int i = 0; i < aenumeratedtype.length; ++i) {
                    vector.addElement(aenumeratedtype[i].getDisplay(locale));
                }
            }
        }

        return vector;
    }

    public Vector filtrateResult(String sendType) {
        Vector vector = new Vector();
        boolean flag = false;
        new Vector();
        if (this.results == null && sendType == null) {
            return vector;
        } else {
            Vector lifecyclevector = this.getSendState(sendType, "1");
            if (lifecyclevector == null || lifecyclevector.size() == 0) {
                flag = true;
            }

            while(true) {
                Object obj;
                State objstate;
                do {
                    do {
                        if (!this.results.hasMoreElements()) {
                            return vector;
                        }

                        obj = this.results.nextElement();
                        objstate = null;
                    } while(!(obj instanceof LifeCycleManaged));

                    LifeCycleManaged lcm = (LifeCycleManaged)obj;
                    objstate = lcm.getLifeCycleState();
                } while((objstate == null || !lifecyclevector.contains(objstate)) && !flag);

                vector.add(obj);
            }
        }
    }

    public Vector getSendState(String sendType, String stateFormat) {
        logger.debug("sendType：" + sendType + "........");
        if (!RemoteMethodServer.ServerFlag) {
            try {
                Class[] argTypes = new Class[]{String.class, String.class};
                Object[] args = new Object[]{sendType, stateFormat};
                return (Vector)RemoteMethodServer.getDefault().invoke("getSendState", CLASSNAME, this, argTypes, args);
            } catch (Exception var15) {
                var15.printStackTrace();
                return null;
            }
        } else {
            logger.debug("sendType：" + sendType + "........");
            Vector stateName = new Vector();
            Vector stateValue = new Vector();
            Map incluedState = new HashMap();
            Map excluedState = new HashMap();
            Map allState = new HashMap();
            Map tempState = new HashMap();
            if (sendType == null || sendType.length() == 0) {
                sendType = "FF";
            }

            if (incluedState != null) {
                tempState = incluedState;
            } else if (excluedState != null && allState != null) {
                tempState = this.fileterState(allState, excluedState);
            } else if (allState != null) {
                tempState = allState;
            }

            logger.debug("incluedState" + incluedState);
            logger.debug("excluedState" + excluedState);
            logger.debug("allState" + allState);
            Set s = ((Map)tempState).keySet();
            Iterator it = s.iterator();
            State[] allstate = State.getStateSet();

            while(true) {
                while(true) {
                    String statename;
                    do {
                        do {
                            if (!it.hasNext()) {
                                if (stateFormat.equals("0")) {
                                    return stateName;
                                }

                                if (stateFormat.equals("1")) {
                                    return stateValue;
                                }

                                return null;
                            }

                            statename = (String)it.next();
                        } while(statename.equals(""));

                        stateName.add(statename);
                    } while(allstate == null);

                    for(int i = 0; i < allstate.length; ++i) {
                        if (allstate[i].getDisplay().equalsIgnoreCase(statename) && !stateValue.contains(allstate[i])) {
                            stateValue.add(allstate[i]);
                            break;
                        }
                    }
                }
            }
        }
    }

    public Map fileterState(Map allState, Map excludeState) {
        Set s = excludeState.keySet();
        Iterator it = s.iterator();

        while(it.hasNext()) {
            allState.remove(it.next());
        }

        return allState;
    }
}
