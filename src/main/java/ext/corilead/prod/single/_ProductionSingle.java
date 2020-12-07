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
import java.util.Vector;
import wt.access.AclEntrySet;
import wt.access.PolicyAccessControlled;
import wt.access.SecurityLabeled;
import wt.access.SecurityLabels;
import wt.admin.AdminDomainRef;
import wt.admin.DomainAdministered;
import wt.content.ContentHolder;
import wt.content.HttpContentOperation;
import wt.fc.EnumeratedTypeUtil;
import wt.fc.EvolvableHelper;
import wt.fc.IdentityHelper;
import wt.fc.PersistenceHelper;
import wt.fc.WTObject;
import wt.fc.adminlock.AdministrativeLock;
import wt.folder.CabinetReference;
import wt.folder.Foldered;
import wt.folder.FolderingInfo;
import wt.folder.SubFolderReference;
import wt.iba.value.AttributeContainer;
import wt.inf.container.WTContained;
import wt.inf.container.WTContainer;
import wt.inf.container.WTContainerRef;
import wt.introspection.ClassInfo;
import wt.introspection.PropertyDisplayName;
import wt.introspection.WTIntrospectionException;
import wt.introspection.WTIntrospector;
import wt.lifecycle.LifeCycleManaged;
import wt.lifecycle.LifeCycleState;
import wt.lifecycle.LifeCycleTemplateReference;
import wt.lifecycle.State;
import wt.ownership.Ownable;
import wt.ownership.Ownership;
import wt.pds.PDSObjectInput;
import wt.pds.PDSObjectOutput;
import wt.pds.PersistentRetrieveIfc;
import wt.pds.PersistentStoreIfc;
import wt.pom.DatastoreException;
import wt.team.TeamReference;
import wt.team.TeamTemplateReference;
import wt.type.TypeDefinitionReference;
import wt.type.Typed;
import wt.util.WTException;
import wt.util.WTInvalidParameterException;
import wt.util.WTPropertyVetoException;
import wt.util.WTStringUtilities;

public abstract class _ProductionSingle extends WTObject implements Typed, ContentHolder, Ownable, Foldered, DomainAdministered, WTContained, PolicyAccessControlled, SecurityLabeled, LifeCycleManaged, Externalizable {
    static final long serialVersionUID = 1L;
    static final String RESOURCE = "ext.corilead.prod.single.singleResource";
    static final String CLASSNAME = ProductionSingle.class.getName();
    public static final String NUMBER = "number";
    static int NUMBER_UPPER_LIMIT = -1;
    String number;
    public static final String PRODUCT_CODE = "productCode";
    static int PRODUCT_CODE_UPPER_LIMIT = -1;
    String productCode;
    public static final String PRODUCTION_UNIT = "productionUnit";
    static int PRODUCTION_UNIT_UPPER_LIMIT = -1;
    String productionUnit;
    public static final String NAME = "name";
    static int NAME_UPPER_LIMIT = -1;
    String name;
    public static final String TELEPHONE = "telephone";
    static int TELEPHONE_UPPER_LIMIT = -1;
    String telephone;
    public static final String REMARKS = "remarks";
    static int REMARKS_UPPER_LIMIT = -1;
    String remarks;
    public static final String NUMBER_OF_BACKUPS = "numberOfBackups";
    static int NUMBER_OF_BACKUPS_UPPER_LIMIT = -1;
    String numberOfBackups;
    public static final String KIND = "kind";
    ProductionSingleKind kind;
    TypeDefinitionReference typeDefinitionReference;
    AttributeContainer theAttributeContainer;
    Vector contentVector;
    boolean hasContents;
    HttpContentOperation operation;
    Vector httpVector;
    Ownership ownership;
    FolderingInfo folderingInfo;
    boolean inheritedDomain;
    AdminDomainRef domainRef;
    WTContainerRef containerReference;
    AdministrativeLock administrativeLock;
    SecurityLabels securityLabels;
    LifeCycleState state;
    AclEntrySet entrySet;
    TeamTemplateReference teamTemplateId;
    TeamReference teamId;
    public static final long EXTERNALIZATION_VERSION_UID = 1381564441914090776L;

    public _ProductionSingle() {
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) throws WTPropertyVetoException {
        this.numberValidate(number);
        this.number = number != null ? number.toUpperCase() : null;
    }

    void numberValidate(String number) throws WTPropertyVetoException {
        if (!IdentityHelper.isChangeable(this)) {
            throw new WTPropertyVetoException("wt.fc.fcResource", "36", new Object[]{new PropertyDisplayName(CLASSNAME, "number")}, new PropertyChangeEvent(this, "number", this.number, number));
        } else {
            if (NUMBER_UPPER_LIMIT < 1) {
                try {
                    NUMBER_UPPER_LIMIT = (Integer)WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("number").getValue("UpperLimit");
                } catch (WTIntrospectionException var3) {
                    NUMBER_UPPER_LIMIT = 200;
                }
            }

            if (number != null && !PersistenceHelper.checkStoredLength(number.toString(), NUMBER_UPPER_LIMIT, true)) {
                throw new WTPropertyVetoException("wt.introspection.introspectionResource", "20", new Object[]{new PropertyDisplayName(CLASSNAME, "number"), String.valueOf(Math.min(NUMBER_UPPER_LIMIT, PersistenceHelper.DB_MAX_SQL_STRING_SIZE / PersistenceHelper.DB_MAX_BYTES_PER_CHAR))}, new PropertyChangeEvent(this, "number", this.number, number));
            }
        }
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) throws WTPropertyVetoException {
        this.productCodeValidate(productCode);
        this.productCode = productCode;
    }

    void productCodeValidate(String productCode) throws WTPropertyVetoException {
        if (PRODUCT_CODE_UPPER_LIMIT < 1) {
            try {
                PRODUCT_CODE_UPPER_LIMIT = (Integer)WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("productCode").getValue("UpperLimit");
            } catch (WTIntrospectionException var3) {
                PRODUCT_CODE_UPPER_LIMIT = 40;
            }
        }

        if (productCode != null && !PersistenceHelper.checkStoredLength(productCode.toString(), PRODUCT_CODE_UPPER_LIMIT, true)) {
            throw new WTPropertyVetoException("wt.introspection.introspectionResource", "20", new Object[]{new PropertyDisplayName(CLASSNAME, "productCode"), String.valueOf(Math.min(PRODUCT_CODE_UPPER_LIMIT, PersistenceHelper.DB_MAX_SQL_STRING_SIZE / PersistenceHelper.DB_MAX_BYTES_PER_CHAR))}, new PropertyChangeEvent(this, "productCode", this.productCode, productCode));
        }
    }

    public String getProductionUnit() {
        return this.productionUnit;
    }

    public void setProductionUnit(String productionUnit) throws WTPropertyVetoException {
        this.productionUnitValidate(productionUnit);
        this.productionUnit = productionUnit;
    }

    void productionUnitValidate(String productionUnit) throws WTPropertyVetoException {
        if (productionUnit != null && productionUnit.trim().length() != 0) {
            if (PRODUCTION_UNIT_UPPER_LIMIT < 1) {
                try {
                    PRODUCTION_UNIT_UPPER_LIMIT = (Integer)WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("productionUnit").getValue("UpperLimit");
                } catch (WTIntrospectionException var3) {
                    PRODUCTION_UNIT_UPPER_LIMIT = 60;
                }
            }

            if (productionUnit != null && !PersistenceHelper.checkStoredLength(productionUnit.toString(), PRODUCTION_UNIT_UPPER_LIMIT, true)) {
                throw new WTPropertyVetoException("wt.introspection.introspectionResource", "20", new Object[]{new PropertyDisplayName(CLASSNAME, "productionUnit"), String.valueOf(Math.min(PRODUCTION_UNIT_UPPER_LIMIT, PersistenceHelper.DB_MAX_SQL_STRING_SIZE / PersistenceHelper.DB_MAX_BYTES_PER_CHAR))}, new PropertyChangeEvent(this, "productionUnit", this.productionUnit, productionUnit));
            }
        } else {
            throw new WTPropertyVetoException("wt.fc.fcResource", "22", new Object[]{new PropertyDisplayName(CLASSNAME, "productionUnit")}, new PropertyChangeEvent(this, "productionUnit", this.productionUnit, productionUnit));
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) throws WTPropertyVetoException {
        this.nameValidate(name);
        this.name = name;
    }

    void nameValidate(String name) throws WTPropertyVetoException {
        if (name != null && name.trim().length() != 0) {
            if (NAME_UPPER_LIMIT < 1) {
                try {
                    NAME_UPPER_LIMIT = (Integer)WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("name").getValue("UpperLimit");
                } catch (WTIntrospectionException var3) {
                    NAME_UPPER_LIMIT = 60;
                }
            }

            if (name != null && !PersistenceHelper.checkStoredLength(name.toString(), NAME_UPPER_LIMIT, true)) {
                throw new WTPropertyVetoException("wt.introspection.introspectionResource", "20", new Object[]{new PropertyDisplayName(CLASSNAME, "name"), String.valueOf(Math.min(NAME_UPPER_LIMIT, PersistenceHelper.DB_MAX_SQL_STRING_SIZE / PersistenceHelper.DB_MAX_BYTES_PER_CHAR))}, new PropertyChangeEvent(this, "name", this.name, name));
            }
        } else {
            throw new WTPropertyVetoException("wt.fc.fcResource", "22", new Object[]{new PropertyDisplayName(CLASSNAME, "name")}, new PropertyChangeEvent(this, "name", this.name, name));
        }
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) throws WTPropertyVetoException {
        this.telephoneValidate(telephone);
        this.telephone = telephone;
    }

    void telephoneValidate(String telephone) throws WTPropertyVetoException {
        if (telephone != null && telephone.trim().length() != 0) {
            if (TELEPHONE_UPPER_LIMIT < 1) {
                try {
                    TELEPHONE_UPPER_LIMIT = (Integer)WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("telephone").getValue("UpperLimit");
                } catch (WTIntrospectionException var3) {
                    TELEPHONE_UPPER_LIMIT = 60;
                }
            }

            if (telephone != null && !PersistenceHelper.checkStoredLength(telephone.toString(), TELEPHONE_UPPER_LIMIT, true)) {
                throw new WTPropertyVetoException("wt.introspection.introspectionResource", "20", new Object[]{new PropertyDisplayName(CLASSNAME, "telephone"), String.valueOf(Math.min(TELEPHONE_UPPER_LIMIT, PersistenceHelper.DB_MAX_SQL_STRING_SIZE / PersistenceHelper.DB_MAX_BYTES_PER_CHAR))}, new PropertyChangeEvent(this, "telephone", this.telephone, telephone));
            }
        } else {
            throw new WTPropertyVetoException("wt.fc.fcResource", "22", new Object[]{new PropertyDisplayName(CLASSNAME, "telephone")}, new PropertyChangeEvent(this, "telephone", this.telephone, telephone));
        }
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) throws WTPropertyVetoException {
        this.remarksValidate(remarks);
        this.remarks = remarks;
    }

    void remarksValidate(String remarks) throws WTPropertyVetoException {
        if (remarks != null && remarks.trim().length() != 0) {
            if (REMARKS_UPPER_LIMIT < 1) {
                try {
                    REMARKS_UPPER_LIMIT = (Integer)WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("remarks").getValue("UpperLimit");
                } catch (WTIntrospectionException var3) {
                    REMARKS_UPPER_LIMIT = 60;
                }
            }

            if (remarks != null && !PersistenceHelper.checkStoredLength(remarks.toString(), REMARKS_UPPER_LIMIT, true)) {
                throw new WTPropertyVetoException("wt.introspection.introspectionResource", "20", new Object[]{new PropertyDisplayName(CLASSNAME, "remarks"), String.valueOf(Math.min(REMARKS_UPPER_LIMIT, PersistenceHelper.DB_MAX_SQL_STRING_SIZE / PersistenceHelper.DB_MAX_BYTES_PER_CHAR))}, new PropertyChangeEvent(this, "remarks", this.remarks, remarks));
            }
        } else {
            throw new WTPropertyVetoException("wt.fc.fcResource", "22", new Object[]{new PropertyDisplayName(CLASSNAME, "remarks")}, new PropertyChangeEvent(this, "remarks", this.remarks, remarks));
        }
    }

    public String getNumberOfBackups() {
        return this.numberOfBackups;
    }

    public void setNumberOfBackups(String numberOfBackups) throws WTPropertyVetoException {
        this.numberOfBackupsValidate(numberOfBackups);
        this.numberOfBackups = numberOfBackups;
    }

    void numberOfBackupsValidate(String numberOfBackups) throws WTPropertyVetoException {
        if (numberOfBackups != null && numberOfBackups.trim().length() != 0) {
            if (NUMBER_OF_BACKUPS_UPPER_LIMIT < 1) {
                try {
                    NUMBER_OF_BACKUPS_UPPER_LIMIT = (Integer)WTIntrospector.getClassInfo(CLASSNAME).getPropertyDescriptor("numberOfBackups").getValue("UpperLimit");
                } catch (WTIntrospectionException var3) {
                    NUMBER_OF_BACKUPS_UPPER_LIMIT = 60;
                }
            }

            if (numberOfBackups != null && !PersistenceHelper.checkStoredLength(numberOfBackups.toString(), NUMBER_OF_BACKUPS_UPPER_LIMIT, true)) {
                throw new WTPropertyVetoException("wt.introspection.introspectionResource", "20", new Object[]{new PropertyDisplayName(CLASSNAME, "numberOfBackups"), String.valueOf(Math.min(NUMBER_OF_BACKUPS_UPPER_LIMIT, PersistenceHelper.DB_MAX_SQL_STRING_SIZE / PersistenceHelper.DB_MAX_BYTES_PER_CHAR))}, new PropertyChangeEvent(this, "numberOfBackups", this.numberOfBackups, numberOfBackups));
            }
        } else {
            throw new WTPropertyVetoException("wt.fc.fcResource", "22", new Object[]{new PropertyDisplayName(CLASSNAME, "numberOfBackups")}, new PropertyChangeEvent(this, "numberOfBackups", this.numberOfBackups, numberOfBackups));
        }
    }

    public ProductionSingleKind getKind() {
        return this.kind;
    }

    public void setKind(ProductionSingleKind kind) throws WTPropertyVetoException {
        this.kindValidate(kind);
        this.kind = kind;
    }

    void kindValidate(ProductionSingleKind kind) throws WTPropertyVetoException {
    }

    public TypeDefinitionReference getTypeDefinitionReference() {
        return this.typeDefinitionReference;
    }

    public void setTypeDefinitionReference(TypeDefinitionReference typeDefinitionReference) throws WTPropertyVetoException {
        this.typeDefinitionReferenceValidate(typeDefinitionReference);
        this.typeDefinitionReference = typeDefinitionReference;
    }

    void typeDefinitionReferenceValidate(TypeDefinitionReference typeDefinitionReference) throws WTPropertyVetoException {
        if (typeDefinitionReference == null) {
            throw new WTPropertyVetoException("wt.fc.fcResource", "22", new Object[]{new PropertyDisplayName(CLASSNAME, "typeDefinitionReference")}, new PropertyChangeEvent(this, "typeDefinitionReference", this.typeDefinitionReference, typeDefinitionReference));
        }
    }

    public AttributeContainer getAttributeContainer() {
        return this.theAttributeContainer;
    }

    public void setAttributeContainer(AttributeContainer theAttributeContainer) {
        this.theAttributeContainer = theAttributeContainer;
    }

    public Vector getContentVector() {
        return this.contentVector;
    }

    public void setContentVector(Vector contentVector) throws WTPropertyVetoException {
        this.contentVectorValidate(contentVector);
        this.contentVector = contentVector;
    }

    void contentVectorValidate(Vector contentVector) throws WTPropertyVetoException {
    }

    public boolean isHasContents() {
        return this.hasContents;
    }

    public void setHasContents(boolean hasContents) throws WTPropertyVetoException {
        this.hasContentsValidate(hasContents);
        this.hasContents = hasContents;
    }

    void hasContentsValidate(boolean hasContents) throws WTPropertyVetoException {
    }

    public HttpContentOperation getOperation() {
        return this.operation;
    }

    public void setOperation(HttpContentOperation operation) throws WTPropertyVetoException {
        this.operationValidate(operation);
        this.operation = operation;
    }

    void operationValidate(HttpContentOperation operation) throws WTPropertyVetoException {
    }

    public Vector getHttpVector() {
        return this.httpVector;
    }

    public void setHttpVector(Vector httpVector) throws WTPropertyVetoException {
        this.httpVectorValidate(httpVector);
        this.httpVector = httpVector;
    }

    void httpVectorValidate(Vector httpVector) throws WTPropertyVetoException {
    }

    public Ownership getOwnership() {
        return this.ownership;
    }

    public void setOwnership(Ownership ownership) {
        this.ownership = ownership;
    }

    public FolderingInfo getFolderingInfo() {
        return this.folderingInfo;
    }

    public void setFolderingInfo(FolderingInfo folderingInfo) {
        this.folderingInfo = folderingInfo;
    }

    public String getCabinetName() {
        try {
            return this.getFolderingInfo().getCabinet().getName();
        } catch (NullPointerException var2) {
            return null;
        }
    }

    public CabinetReference getCabinet() {
        try {
            return this.getFolderingInfo().getCabinet();
        } catch (NullPointerException var2) {
            return null;
        }
    }

    public SubFolderReference getParentFolder() {
        try {
            return this.getFolderingInfo().getParentFolder();
        } catch (NullPointerException var2) {
            return null;
        }
    }

    public boolean isInheritedDomain() {
        return this.inheritedDomain;
    }

    public void setInheritedDomain(boolean inheritedDomain) throws WTPropertyVetoException {
        this.inheritedDomainValidate(inheritedDomain);
        this.inheritedDomain = inheritedDomain;
    }

    void inheritedDomainValidate(boolean inheritedDomain) throws WTPropertyVetoException {
    }

    public AdminDomainRef getDomainRef() {
        return this.domainRef;
    }

    public void setDomainRef(AdminDomainRef domainRef) {
        this.domainRef = domainRef;
    }

    public String getContainerName() {
        try {
            return this.getContainerReference().getName();
        } catch (NullPointerException var2) {
            return null;
        }
    }

    public WTContainer getContainer() {
        return this.containerReference != null ? (WTContainer)this.containerReference.getObject() : null;
    }

    public WTContainerRef getContainerReference() {
        return this.containerReference;
    }

    public void setContainer(WTContainer the_container) throws WTPropertyVetoException, WTException {
        this.setContainerReference(the_container == null ? null : WTContainerRef.newWTContainerRef(the_container));
    }

    public void setContainerReference(WTContainerRef the_containerReference) throws WTPropertyVetoException {
        this.containerReferenceValidate(the_containerReference);
        this.containerReference = the_containerReference;
    }

    void containerReferenceValidate(WTContainerRef the_containerReference) throws WTPropertyVetoException {
        if (the_containerReference != null && the_containerReference.getReferencedClass() != null) {
            if (the_containerReference != null && the_containerReference.getReferencedClass() != null && !WTContainer.class.isAssignableFrom(the_containerReference.getReferencedClass())) {
                throw new WTPropertyVetoException("wt.introspection.introspectionResource", "35", new Object[]{new PropertyDisplayName(CLASSNAME, "containerReference"), "WTContainerRef"}, new PropertyChangeEvent(this, "containerReference", this.containerReference, this.containerReference));
            }
        } else {
            throw new WTPropertyVetoException("wt.introspection.introspectionResource", "22", new Object[]{new PropertyDisplayName(CLASSNAME, "containerReference")}, new PropertyChangeEvent(this, "containerReference", this.containerReference, this.containerReference));
        }
    }

    public AdministrativeLock getAdministrativeLock() {
        return this.administrativeLock;
    }

    public void setAdministrativeLock(AdministrativeLock administrativeLock) throws WTPropertyVetoException {
        this.administrativeLockValidate(administrativeLock);
        this.administrativeLock = administrativeLock;
    }

    void administrativeLockValidate(AdministrativeLock administrativeLock) throws WTPropertyVetoException {
    }

    public SecurityLabels getSecurityLabels() {
        return this.securityLabels;
    }

    public void setSecurityLabels(SecurityLabels securityLabels) {
        this.securityLabels = securityLabels;
    }

    public LifeCycleState getState() {
        return this.state;
    }

    public void setState(LifeCycleState state) throws WTPropertyVetoException {
        this.stateValidate(state);
        this.state = state;
    }

    void stateValidate(LifeCycleState state) throws WTPropertyVetoException {
        if (state == null) {
            throw new WTPropertyVetoException("wt.fc.fcResource", "22", new Object[]{new PropertyDisplayName(CLASSNAME, "state")}, new PropertyChangeEvent(this, "state", this.state, state));
        }
    }

    public String getLifeCycleName() {
        try {
            return this.getState().getLifeCycleId().getName();
        } catch (NullPointerException var2) {
            return null;
        }
    }

    public State getLifeCycleState() {
        try {
            return this.getState().getState();
        } catch (NullPointerException var2) {
            return null;
        }
    }

    public boolean isLifeCycleAtGate() {
        try {
            return this.getState().isAtGate();
        } catch (NullPointerException var2) {
            return false;
        }
    }

    public LifeCycleTemplateReference getLifeCycleTemplate() {
        try {
            return this.getState().getLifeCycleId();
        } catch (NullPointerException var2) {
            return null;
        }
    }

    public boolean isLifeCycleBasic() {
        try {
            return this.getState().getLifeCycleId().isBasic();
        } catch (NullPointerException var2) {
            return false;
        }
    }

    public AclEntrySet getEntrySet() {
        return this.entrySet;
    }

    public void setEntrySet(AclEntrySet entrySet) {
        this.entrySet = entrySet;
    }

    public TeamTemplateReference getTeamTemplateId() {
        return this.teamTemplateId;
    }

    public void setTeamTemplateId(TeamTemplateReference teamTemplateId) throws WTPropertyVetoException {
        this.teamTemplateIdValidate(teamTemplateId);
        this.teamTemplateId = teamTemplateId;
    }

    void teamTemplateIdValidate(TeamTemplateReference teamTemplateId) throws WTPropertyVetoException {
    }

    public TeamReference getTeamId() {
        return this.teamId;
    }

    public void setTeamId(TeamReference teamId) throws WTPropertyVetoException {
        this.teamIdValidate(teamId);
        this.teamId = teamId;
    }

    void teamIdValidate(TeamReference teamId) throws WTPropertyVetoException {
    }

    public String getTeamName() {
        try {
            return this.getTeamId().getName();
        } catch (NullPointerException var2) {
            return null;
        }
    }

    public String getTeamIdentity() {
        try {
            return this.getTeamId().getIdentity();
        } catch (NullPointerException var2) {
            return null;
        }
    }

    public String getTeamTemplateName() {
        try {
            return this.getTeamTemplateId().getName();
        } catch (NullPointerException var2) {
            return null;
        }
    }

    public String getTeamTemplateIdentity() {
        try {
            return this.getTeamTemplateId().getIdentity();
        } catch (NullPointerException var2) {
            return null;
        }
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
        output.writeLong(1381564441914090776L);
        super.writeExternal(output);
        output.writeObject(this.administrativeLock);
        output.writeObject(this.containerReference);
        output.writeObject(this.domainRef);
        output.writeObject(this.entrySet);
        output.writeObject(this.folderingInfo);
        output.writeBoolean(this.inheritedDomain);
        output.writeObject(this.kind == null ? null : this.kind.getStringValue());
        output.writeObject(this.name);
        output.writeObject(this.number);
        output.writeObject(this.numberOfBackups);
        output.writeObject(this.ownership);
        output.writeObject(this.productCode);
        output.writeObject(this.productionUnit);
        output.writeObject(this.remarks);
        output.writeObject(this.securityLabels);
        output.writeObject(this.state);
        output.writeObject(this.teamId);
        output.writeObject(this.teamTemplateId);
        output.writeObject(this.telephone);
        output.writeObject(this.typeDefinitionReference);
        if (!(output instanceof PDSObjectOutput)) {
            output.writeObject(this.contentVector);
            output.writeBoolean(this.hasContents);
            output.writeObject(this.httpVector);
            output.writeObject(this.operation);
            output.writeObject(this.theAttributeContainer);
        }

    }

    protected void super_writeExternal_ProductionSingle(ObjectOutput output) throws IOException {
        super.writeExternal(output);
    }

    public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {
        long readSerialVersionUID = input.readLong();
        this.readVersion((ProductionSingle)this, input, readSerialVersionUID, false, false);
    }

    protected void super_readExternal_ProductionSingle(ObjectInput input) throws IOException, ClassNotFoundException {
        super.readExternal(input);
    }

    public void writeExternal(PersistentStoreIfc output) throws SQLException, DatastoreException {
        super.writeExternal(output);
        output.writeObject("administrativeLock", this.administrativeLock, AdministrativeLock.class, false);
        output.writeObject("containerReference", this.containerReference, WTContainerRef.class, true);
        output.writeObject("domainRef", this.domainRef, AdminDomainRef.class, true);
        output.setInlineObject("entrySet", "blob$entrySet", this.entrySet);
        output.writeObject("folderingInfo", this.folderingInfo, FolderingInfo.class, true);
        output.setBoolean("inheritedDomain", this.inheritedDomain);
        output.setString("kind", this.kind == null ? null : this.kind.toString());
        output.setString("name", this.name);
        output.setString("number", this.number);
        output.setString("numberOfBackups", this.numberOfBackups);
        output.writeObject("ownership", this.ownership, Ownership.class, true);
        output.setString("productCode", this.productCode);
        output.setString("productionUnit", this.productionUnit);
        output.setString("remarks", this.remarks);
        output.writeObject("securityLabels", this.securityLabels, SecurityLabels.class, true);
        output.writeObject("state", this.state, LifeCycleState.class, true);
        output.writeObject("teamId", this.teamId, TeamReference.class, false);
        output.writeObject("teamTemplateId", this.teamTemplateId, TeamTemplateReference.class, false);
        output.setString("telephone", this.telephone);
        output.writeObject("typeDefinitionReference", this.typeDefinitionReference, TypeDefinitionReference.class, true);
    }

    public void readExternal(PersistentRetrieveIfc input) throws SQLException, DatastoreException {
        super.readExternal(input);
        this.administrativeLock = (AdministrativeLock)input.readObject("administrativeLock", this.administrativeLock, AdministrativeLock.class, false);
        this.containerReference = (WTContainerRef)input.readObject("containerReference", this.containerReference, WTContainerRef.class, true);
        this.domainRef = (AdminDomainRef)input.readObject("domainRef", this.domainRef, AdminDomainRef.class, true);
        this.entrySet = (AclEntrySet)input.getInlineObject("entrySet", "blob$entrySet");
        this.folderingInfo = (FolderingInfo)input.readObject("folderingInfo", this.folderingInfo, FolderingInfo.class, true);
        this.inheritedDomain = input.getBoolean("inheritedDomain");
        String kind_string_value = input.getString("kind");
        if (kind_string_value != null) {
            this.kind = (ProductionSingleKind)ClassInfo.getConstrainedEnum(this.getClass(), "kind", kind_string_value);
            if (this.kind == null) {
                this.kind = ProductionSingleKind.toProductionSingleKind(kind_string_value);
            }
        }

        this.name = input.getString("name");
        this.number = input.getString("number");
        this.numberOfBackups = input.getString("numberOfBackups");
        this.ownership = (Ownership)input.readObject("ownership", this.ownership, Ownership.class, true);
        this.productCode = input.getString("productCode");
        this.productionUnit = input.getString("productionUnit");
        this.remarks = input.getString("remarks");
        this.securityLabels = (SecurityLabels)input.readObject("securityLabels", this.securityLabels, SecurityLabels.class, true);
        this.state = (LifeCycleState)input.readObject("state", this.state, LifeCycleState.class, true);
        this.teamId = (TeamReference)input.readObject("teamId", this.teamId, TeamReference.class, false);
        this.teamTemplateId = (TeamTemplateReference)input.readObject("teamTemplateId", this.teamTemplateId, TeamTemplateReference.class, false);
        this.telephone = input.getString("telephone");
        this.typeDefinitionReference = (TypeDefinitionReference)input.readObject("typeDefinitionReference", this.typeDefinitionReference, TypeDefinitionReference.class, true);
    }

    boolean readVersion1381564441914090776L(ObjectInput input, long readSerialVersionUID, boolean superDone) throws IOException, ClassNotFoundException {
        if (!superDone) {
            super.readExternal(input);
        }

        this.administrativeLock = (AdministrativeLock)input.readObject();
        this.containerReference = (WTContainerRef)input.readObject();
        this.domainRef = (AdminDomainRef)input.readObject();
        this.entrySet = (AclEntrySet)input.readObject();
        this.folderingInfo = (FolderingInfo)input.readObject();
        this.inheritedDomain = input.readBoolean();
        String kind_string_value = (String)input.readObject();

        try {
            this.kind = (ProductionSingleKind)EnumeratedTypeUtil.toEnumeratedType(kind_string_value);
        } catch (WTInvalidParameterException var7) {
            this.kind = ProductionSingleKind.toProductionSingleKind(kind_string_value);
        }

        this.name = (String)input.readObject();
        this.number = (String)input.readObject();
        this.numberOfBackups = (String)input.readObject();
        this.ownership = (Ownership)input.readObject();
        this.productCode = (String)input.readObject();
        this.productionUnit = (String)input.readObject();
        this.remarks = (String)input.readObject();
        this.securityLabels = (SecurityLabels)input.readObject();
        this.state = (LifeCycleState)input.readObject();
        this.teamId = (TeamReference)input.readObject();
        this.teamTemplateId = (TeamTemplateReference)input.readObject();
        this.telephone = (String)input.readObject();
        this.typeDefinitionReference = (TypeDefinitionReference)input.readObject();
        if (!(input instanceof PDSObjectInput)) {
            this.contentVector = (Vector)input.readObject();
            this.hasContents = input.readBoolean();
            this.httpVector = (Vector)input.readObject();
            this.operation = (HttpContentOperation)input.readObject();
            this.theAttributeContainer = (AttributeContainer)input.readObject();
        }

        return true;
    }

    protected boolean readVersion(ProductionSingle thisObject, ObjectInput input, long readSerialVersionUID, boolean passThrough, boolean superDone) throws IOException, ClassNotFoundException {
        boolean success = true;
        if (readSerialVersionUID == 1381564441914090776L) {
            return this.readVersion1381564441914090776L(input, readSerialVersionUID, superDone);
        } else {
            success = this.readOldVersion(input, readSerialVersionUID, passThrough, superDone);
            if (input instanceof PDSObjectInput) {
                EvolvableHelper.requestRewriteOfEvolvedBlobbedObject();
            }

            return success;
        }
    }

    protected boolean super_readVersion_ProductionSingle(_ProductionSingle thisObject, ObjectInput input, long readSerialVersionUID, boolean passThrough, boolean superDone) throws IOException, ClassNotFoundException {
        return super.readVersion(thisObject, input, readSerialVersionUID, passThrough, superDone);
    }

    boolean readOldVersion(ObjectInput input, long readSerialVersionUID, boolean passThrough, boolean superDone) throws IOException, ClassNotFoundException {
        throw new InvalidClassException(CLASSNAME, "Local class not compatible: stream classdesc externalizationVersionUID=" + readSerialVersionUID + " local class externalizationVersionUID=" + 1381564441914090776L);
    }
}
