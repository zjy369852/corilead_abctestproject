package ext.corilead.prod.single.mvc.builders;


import com.ptc.core.ui.resources.ComponentType;
import com.ptc.jca.mvc.components.AbstractAttributesComponentBuilder;
import com.ptc.jca.mvc.components.JcaAttributeConfig;
import com.ptc.jca.mvc.components.JcaGroupConfig;
import ext.corilead.prod.single.ProductionSingle;
import wt.util.WTException;

import com.ptc.mvc.components.AttributePanelConfig;
import com.ptc.mvc.components.ComponentBuilder;
import com.ptc.mvc.components.ComponentConfigFactory;
import com.ptc.mvc.components.ComponentId;
import com.ptc.mvc.components.ComponentParams;
import com.ptc.mvc.components.TypeBased;
import wt.vc.baseline.ManagedBaseline;

@ComponentBuilder("corilead.prod.single.ProductionSingleSetAttibutes")
@TypeBased("ext.corilead.prod.single.ProductionSingle")
public class ProductionSingleSetAttibutes extends AbstractAttributesComponentBuilder {
    @Override
    protected AttributePanelConfig buildAttributesComponentConfig(final ComponentParams params) throws WTException {
        final ComponentConfigFactory factory = getComponentConfigFactory();
        final AttributePanelConfig panel; {
            panel = factory.newAttributePanelConfig
                    (ComponentId.ATTRIBUTE_PANEL_ID);
            panel.setComponentType
                    (ComponentType.WIZARD_ATTRIBUTES_TABLE);
            final JcaGroupConfig group; {
                group = (JcaGroupConfig) factory.newGroupConfig();
                group.setId("01");
                group.setLabel("新建投产单");
                group.setIsGridLayout(true);
                //.setLabel("编号")
                group.addComponent(getAttribute(ProductionSingle.NAME,"名称", factory));
                group.addComponent(getAttribute(ProductionSingle.TELEPHONE,"电话", factory));
                group.addComponent(getAttribute(ProductionSingle.PRODUCT_CODE,"产品代号", factory));
                //group.addComponent(getAttribute(ProductionSingle.PRODUCTION_UNIT,"投产单位", factory));
                //group.addComponent(getAttribute(ProductionSingle.NUMBER_OF_BACKUPS,"备份数量", factory));
                group.addComponent(getAttribute(ProductionSingle.KIND, "选择基线",factory));
                group.addComponent(getAttribute(ProductionSingle.REMARKS, "备注",factory));
            }
            panel.addComponent(group);
        }
        return panel;
    }
    JcaAttributeConfig getAttribute
            (final String id, final String lable,final ComponentConfigFactory
                    factory) {
        final JcaAttributeConfig attribute = (JcaAttributeConfig)
                factory.newAttributeConfig();
        attribute.setId(id);
        attribute.setLabel(lable);
        return attribute;
    }
}

