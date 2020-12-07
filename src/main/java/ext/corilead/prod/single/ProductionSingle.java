package ext.corilead.prod.single;
import com.ptc.windchill.annotations.metadata.*;

import com.ptc.windchill.enterprise.doc.mvc.builders.wizards.DocTypedAttributePanelBuilder;
import com.ptc.windchill.wp.WorkPackage;
import wt.access.PolicyAccessControlled;
import wt.access.SecurityLabeled;
import wt.admin.DomainAdministered;
import wt.associativity.Associative;
import wt.content.ContentHolder;

import wt.fc.WTObject;

import wt.folder.CabinetReference;
import wt.folder.FolderHelper;
import wt.folder.Foldered;
import wt.folder.folderResource;
import wt.inf.container.WTContained;
import wt.lifecycle.LifeCycleManaged;

import wt.ownership.Ownable;

import wt.part.WTPart;
import wt.type.TypeDefinitionInfo;

import wt.util.WTException;

import wt.util.WTRuntimeException;
import wt.type.Typed;

//PolicyAccessControlled策略访问控制SecurityLabeled安全标签LifeCycleManaged生命周期

@GenAsPersistable(superClass = WTObject.class,
        interfaces = {Typed.class,ContentHolder.class, Ownable.class,Foldered.class, DomainAdministered.class,WTContained.class,
                PolicyAccessControlled.class, SecurityLabeled.class,LifeCycleManaged.class,},
        properties = {
                @GeneratedProperty(name = "number",
                                    type = String.class,
                                    supportedAPI = SupportedAPI.PUBLIC,
                                    javaDoc = "Unique identifier.",
                                     constraints = @PropertyConstraints(
                stringCase = StringCase.UPPER_CASE,
                changeable = Changeable.VIA_OTHER_MEANS
        ),columnProperties = @ColumnProperties(
                index = true,
                columnName = "ProductionSingleNumber"
        )
                ), @GeneratedProperty(name="productCode",type = String.class,
                        constraints = @PropertyConstraints(upperLimit = 40)),
                @GeneratedProperty(name = "productionUnit",type = String.class, columnProperties = @ColumnProperties(index = true),
                        constraints = @PropertyConstraints(required = true,upperLimit = 60)),
                @GeneratedProperty(name="name",type = String.class,columnProperties = @ColumnProperties(index = true),
                        constraints = @PropertyConstraints(required = true,upperLimit = 60)),
                @GeneratedProperty(name="telephone",type = String.class,columnProperties = @ColumnProperties(index = true),
                        constraints = @PropertyConstraints(required = true,upperLimit = 60)),
                @GeneratedProperty(name="remarks",type = String.class,columnProperties = @ColumnProperties(index = true),
                        constraints = @PropertyConstraints(required = true,upperLimit = 60)),
                @GeneratedProperty(name="kind",type = ProductionSingleKind.class),
        })

public class ProductionSingle extends _ProductionSingle{
    static final long serialVersionUID = 1;

    public static ProductionSingle newProductionSingle() throws WTException {
        ProductionSingle instance = new ProductionSingle();
        instance.initialize();
        return instance;
    }
    public CabinetReference getCabinetReference() {
        try {
            return FolderHelper.getCabinetReference(this);
        } catch (WTException localWTException) {
            throw new WTRuntimeException(localWTException, folderResource.class.getName(), "41", null);
        }
    }
    public String getLocation() {
        try {
            return FolderHelper.getLocation(this);
        } catch (WTException localWTException) {
            throw new WTRuntimeException(localWTException);
        }
    }
    public String getFolderPath() {
        try {
            return FolderHelper.getFolderPath(this) + getName();
        } catch (WTException localWTException) {
            throw new WTRuntimeException(localWTException);
        }
    }
    public String getName() {
        return super.getName();
    }
    public String getFlexTypeIdPath() {
        return null;
    }

    public Object getValue() {
        return null;
    }
    public void setValue(String paramString1, String paramString2) {

    }
    public TypeDefinitionInfo getTypeDefinitionInfo() {
        return null;
    }

}
