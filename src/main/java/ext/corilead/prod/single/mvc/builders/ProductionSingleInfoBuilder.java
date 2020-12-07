package ext.corilead.prod.single.mvc.builders;
import com.ptc.jca.mvc.builders.TypedAttributesPanelBuilder;
import com.ptc.mvc.components.*;
import com.ptc.windchill.enterprise.doc.mvc.DocumentInfoBuilderHelper;
import org.apache.log4j.Logger;
import wt.log4j.LogR;
import wt.util.WTException;
import com.ptc.jca.mvc.builders.DefaultInfoComponentBuilder;

@ComponentBuilder(ComponentId.INFOPAGE_ID)
@TypeBased("ext.corilead.prod.single.ProductionSingle")
public class ProductionSingleInfoBuilder extends DefaultInfoComponentBuilder {
//    @Override
//    protected InfoConfig buildInfoConfig(final ComponentParams params)
//            throws WTException {
//        final InfoConfig info = getComponentConfigFactory().newInfoConfig();
//        info.setTabSet("petDetails");
//        return info;
//    }
private static final Logger log = LogR.getLogger(ProductionSingleInfoBuilder.class.getName());

    public ProductionSingleInfoBuilder() {

    }

    public InfoConfig buildInfoConfig(ComponentParams var1) throws WTException {
        log.debug("Info page config begin");
        InfoComponentConfigFactory var2 = this.getComponentConfigFactory();
        InfoConfig var3 = DocumentInfoBuilderHelper.buildInfoConfig(var2);
        var3.setNavBarName("third_level_nav_doc");
        var3.setTabSet("docInfoPageTabSet");
        var3.setHelpContext("DocMgmtDocInfoPageRef");
        return var3;
    }


}
