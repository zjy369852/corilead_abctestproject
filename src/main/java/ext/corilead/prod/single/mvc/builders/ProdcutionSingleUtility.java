package ext.corilead.prod.single.mvc.builders;

import ext.corilead.prod.single.Bean.CommonUtil;
import com.ptc.core.components.descriptor.ModelContext;
import com.ptc.core.components.factory.AbstractDataUtility;
import com.ptc.core.components.rendering.guicomponents.*;
import wt.doc.WTDocument;
import wt.epm.EPMDocument;
import wt.fc.Persistable;
import wt.part.WTPart;
import wt.util.WTException;
import java.util.ArrayList;


public class ProdcutionSingleUtility extends AbstractDataUtility {
    /**
     *
     * @param
     * @param o
     * @param
     * @return
     * @throws WTException
     */
    @Override
    public Object getDataValue(String component_id, Object o, ModelContext c) throws WTException {
        GUIComponentArray var4 = new GUIComponentArray();
        String objectname = "";
        if (o instanceof WTPart) {
            WTPart part = (WTPart) o;
            objectname = part.getName();
        }
        if (o instanceof WTDocument) {
            WTDocument document = (WTDocument) o;
            objectname = document.getName();
        }
        if (o instanceof EPMDocument) {
            EPMDocument epmDocument = (EPMDocument) o;
            objectname = epmDocument.getName();
        }

        System.out.println(objectname);
//        UrlDisplayComponent urlDisplayComponent = new UrlDisplayComponent();
//        String infourl = CommonUtil.getInfoPageURL((Persistable) o);
//        urlDisplayComponent.setLink(infourl);
//        urlDisplayComponent.setLabel(objectname);
//        urlDisplayComponent.setTarget("_blank");
//        var4.addGUIComponent(urlDisplayComponent);

        System.out.println("component_id"+component_id);

        if ("productionnum".equals(component_id)) {//发送份数的文本框
            //TextBox ta = ne TextBox();
            NumericInputComponent ta=new NumericInputComponent();
            ta.setId(component_id );
            System.out.println("ta.getId()"+ta.getId());
            ta.setName(component_id);
            ta.setRequired(true);
            var4.addGUIComponent(ta);
        }
        if ("productionUnit".equals(component_id)) {//发送份数的文本框
            //TextBox ta = new TextBox();
            NumericInputComponent ta=new NumericInputComponent();
            ComboBox comboBox = new ComboBox();
            ArrayList<String> list = new ArrayList<String>();


            list.add(WorkflowInfoRB.Siyuan);
            list.add(WorkflowInfoRB.AEROSPACESCIENCEANDENGINEERING);
            list.add(WorkflowInfoRB.CONTROLOFFICE);
            list.add(WorkflowInfoRB.GENERALDEPARTMENT);
            list.add(WorkflowInfoRB.INFORMATIONARCHIVESCENTER);
            ArrayList<String> displayValue = new ArrayList<String>();


            comboBox.setInternalValues(list);
            comboBox.setValues(list);
            comboBox.setSelected(WorkflowInfoRB.Siyuan);
            comboBox.setId(component_id);
            var4.addGUIComponent(comboBox);
        }
        return var4;
    }



    /**
     * 输入框
     * @param value
     * @param name
     * @return
     */
//    private TextBox createTextBox(String value , String name){
//        TextBox tBox= new TextBox();
//        tBox.setId(name);
//        tBox.setName(name);
//        tBox.setValue(value);
//        return tBox;
//    }

    /**
     * 下拉框
     * @param decision
     * @param routeNumber
     * @param resourceBundle
     * @return
     */
//    private ComboBox extract(Object decision , String routeNumber, String resourceBundle){
//
//        ArrayList<String> internalValue = new ArrayList<String>();
//        internalValue.add(WorkflowInfoRB.Siyuan);
//        internalValue.add(WorkflowInfoRB.AEROSPACESCIENCEANDENGINEERING);
//        internalValue.add(WorkflowInfoRB.CONTROLOFFICE);
//        internalValue.add(WorkflowInfoRB.GENERALDEPARTMENT);
//        internalValue.add(WorkflowInfoRB.INFORMATIONARCHIVESCENTER);
//        ArrayList<String> displayValue = new ArrayList<String>();
//        //displayValue.add(resourceBundle.)
//
//        ComboBox comboBox = new ComboBox();
//        comboBox.setInternalValues(internalValue);
//        comboBox.setValues(displayValue);
//        comboBox.setSelected(WorkflowInfoRB.Siyuan);
//
//        return comboBox;
//    }

}
