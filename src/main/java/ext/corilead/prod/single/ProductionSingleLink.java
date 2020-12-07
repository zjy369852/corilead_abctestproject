package ext.corilead.prod.single;

import com.ptc.windchill.annotations.metadata.*;
import wt.fc.ObjectToObjectLink;
import wt.fc.WTObject;
import wt.util.WTException;

@GenAsBinaryLink(superClass=ObjectToObjectLink.class,
        serializable= Serialization.EXTERNALIZABLE_BASIC,
        properties={
                @GeneratedProperty(name="num",type = int.class),
                @GeneratedProperty(name = "productionUnit",type = String.class),
        },
        roleA=@GeneratedRole(name="receiveobjects", type=ProductionSingle.class,
                cardinality=Cardinality.ONE_TO_MANY),
        roleB=@GeneratedRole(name="receivebyobject", type=WTObject.class,
                cardinality= Cardinality.ONE_TO_MANY),
        tableProperties=@TableProperties(tableName="ReceiveObjectLink")

)

public class ProductionSingleLink extends _ProductionSingleLink{


    static final long serialVersionUID = 1;




    /**
     * Default factory for the class.
     *
     * @param
     * @param
     * @return
     * @exception WTException
     **/
    public static ProductionSingleLink newSendObjectLink( ProductionSingle productionsingle, WTObject sendbyobject )
            throws WTException {

        ProductionSingleLink instance = new ProductionSingleLink();
        instance.initialize( productionsingle, sendbyobject );
        return instance;
    }
}
