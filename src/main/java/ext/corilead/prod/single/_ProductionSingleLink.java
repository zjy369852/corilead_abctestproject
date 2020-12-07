//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ext.corilead.prod.single;

import java.beans.PropertyChangeEvent;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.sql.SQLException;
import wt.fc.EvolvableHelper;
import wt.fc.ObjectToObjectLink;
import wt.fc.PersistenceHelper;
import wt.fc.WTObject;
import wt.introspection.ClassInfo;
import wt.introspection.PropertyDisplayName;
import wt.introspection.WTIntrospectionException;
import wt.introspection.WTIntrospector;
import wt.pds.PDSObjectInput;
import wt.pds.PersistentRetrieveIfc;
import wt.pds.PersistentStoreIfc;
import wt.pom.DatastoreException;
import wt.util.WTPropertyVetoException;
import wt.util.WTStringUtilities;

public abstract class _ProductionSingleLink extends ObjectToObjectLink implements Externalizable {
    static final long serialVersionUID = 1L;
    static final String RESOURCE = "ext.corilead.prod.single.singleResource";
    static final String CLASSNAME = ProductionSingleLink.class.getName();
    public static final String NUM = "num";
    int num;
    public static final String PRODUCTION_UNIT = "productionUnit";
    static int PRODUCTION_UNIT_UPPER_LIMIT = -1;
    String productionUnit;
    public static final String RECEIVEOBJECTS_ROLE = "receiveobjects";
    public static final String RECEIVEBYOBJECT_ROLE = "receivebyobject";
    public static final long EXTERNALIZATION_VERSION_UID = 2769777192337733603L;

    public _ProductionSingleLink() {
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) throws WTPropertyVetoException {
        this.numValidate(num);
        this.num = num;
    }

    void numValidate(int num) throws WTPropertyVetoException {
    }

    public String getProductionUnit() {
        return this.productionUnit;
    }

    public void setProductionUnit(String productionUnit) throws WTPropertyVetoException {
        this.productionUnitValidate(productionUnit);
        this.productionUnit = productionUnit;
    }

    void productionUnitValidate(String productionUnit) throws WTPropertyVetoException {
        if (PRODUCTION_UNIT_UPPER_LIMIT < 1) {
            try {
                PRODUCTION_UNIT_UPPER_LIMIT = (Integer)WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("productionUnit").getValue("UpperLimit");
            } catch (WTIntrospectionException var3) {
                PRODUCTION_UNIT_UPPER_LIMIT = 200;
            }
        }

        if (productionUnit != null && !PersistenceHelper.checkStoredLength(productionUnit.toString(), PRODUCTION_UNIT_UPPER_LIMIT, true)) {
            throw new WTPropertyVetoException("wt.introspection.introspectionResource", "20", new Object[]{new PropertyDisplayName(CLASSNAME, "productionUnit"), String.valueOf(Math.min(PRODUCTION_UNIT_UPPER_LIMIT, PersistenceHelper.DB_MAX_SQL_STRING_SIZE / PersistenceHelper.DB_MAX_BYTES_PER_CHAR))}, new PropertyChangeEvent(this, "productionUnit", this.productionUnit, productionUnit));
        }
    }

    public ProductionSingle getReceiveobjects() {
        return (ProductionSingle)this.getRoleAObject();
    }

    public void setReceiveobjects(ProductionSingle the_receiveobjects) throws WTPropertyVetoException {
        this.setRoleAObject(the_receiveobjects);
    }

    public WTObject getReceivebyobject() {
        return (WTObject)this.getRoleBObject();
    }

    public void setReceivebyobject(WTObject the_receivebyobject) throws WTPropertyVetoException {
        this.setRoleBObject(the_receivebyobject);
    }

    public String getConceptualClassname() {
        return CLASSNAME;
    }

    public ClassInfo getClassInfo() throws WTIntrospectionException {
        return WTIntrospector.getClassInfo(this.getConceptualClassname());
    }

    public String getType() {
        try {
            return this.getClassInfo().getDisplayName();
        } catch (WTIntrospectionException var2) {
            return WTStringUtilities.tail(this.getConceptualClassname(), '.');
        }
    }

    public void writeExternal(ObjectOutput output) throws IOException {
        output.writeLong(2769777192337733603L);
        super.writeExternal(output);
        output.writeInt(this.num);
        output.writeObject(this.productionUnit);
    }

    protected void super_writeExternal_ProductionSingleLink(ObjectOutput output) throws IOException {
        super.writeExternal(output);
    }

    public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {
        long readSerialVersionUID = input.readLong();
        this.readVersion((ProductionSingleLink)this, input, readSerialVersionUID, false, false);
    }

    protected void super_readExternal_ProductionSingleLink(ObjectInput input) throws IOException, ClassNotFoundException {
        super.readExternal(input);
    }

    public void writeExternal(PersistentStoreIfc output) throws SQLException, DatastoreException {
        super.writeExternal(output);
        output.setInt("num", this.num);
        output.setString("productionUnit", this.productionUnit);
    }

    public void readExternal(PersistentRetrieveIfc input) throws SQLException, DatastoreException {
        super.readExternal(input);
        this.num = input.getInt("num");
        this.productionUnit = input.getString("productionUnit");
    }

    boolean readVersion2769777192337733603L(ObjectInput input, long readSerialVersionUID, boolean superDone) throws IOException, ClassNotFoundException {
        if (!superDone) {
            super.readExternal(input);
        }

        this.num = input.readInt();
        this.productionUnit = (String)input.readObject();
        return true;
    }

    protected boolean readVersion(ProductionSingleLink thisObject, ObjectInput input, long readSerialVersionUID, boolean passThrough, boolean superDone) throws IOException, ClassNotFoundException {
        boolean success = true;
        if (readSerialVersionUID == 2769777192337733603L) {
            return this.readVersion2769777192337733603L(input, readSerialVersionUID, superDone);
        } else {
            success = this.readOldVersion(input, readSerialVersionUID, passThrough, superDone);
            if (input instanceof PDSObjectInput) {
                EvolvableHelper.requestRewriteOfEvolvedBlobbedObject();
            }

            return success;
        }
    }

    protected boolean super_readVersion_ProductionSingleLink(_ProductionSingleLink thisObject, ObjectInput input, long readSerialVersionUID, boolean passThrough, boolean superDone) throws IOException, ClassNotFoundException {
        return super.readVersion(thisObject, input, readSerialVersionUID, passThrough, superDone);
    }

    boolean readOldVersion(ObjectInput input, long readSerialVersionUID, boolean passThrough, boolean superDone) throws IOException, ClassNotFoundException {
        throw new InvalidClassException(CLASSNAME, "Local class not compatible: stream classdesc externalizationVersionUID=" + readSerialVersionUID + " local class externalizationVersionUID=" + 2769777192337733603L);
    }
}
