//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ext.corilead.prod.single;

import java.util.Hashtable;
import java.util.Locale;
import wt.fc.EnumeratedType;
import wt.util.WTInvalidParameterException;

public abstract class _ProductionSingleKind extends EnumeratedType {
    static final long serialVersionUID = 1L;
    static final String RESOURCE = "ext.corilead.prod.single.singleResource";
    static final String CLASSNAME = (new ProductionSingleKind()).getClass().getName();
    static final String CLASS_RESOURCE = "ext.corilead.prod.single.ProductionSingleKindRB";
    static Hashtable localeSets;
    private static volatile EnumeratedType[] valueSet;

    public _ProductionSingleKind() {
    }

    static EnumeratedType[] _valueSet() {
        if (valueSet == null) {
            Class var0 = _ProductionSingleKind.class;
            synchronized(_ProductionSingleKind.class) {
                try {
                    if (valueSet == null) {
                        valueSet = initializeLocaleSet((Locale)null);
                    }
                } catch (Throwable var3) {
                    throw new ExceptionInInitializerError(var3);
                }
            }
        }

        return valueSet;
    }

    public static ProductionSingleKind newProductionSingleKind(int secretHandshake) throws IllegalAccessException {
        validateFriendship(secretHandshake);
        return new ProductionSingleKind();
    }

    public static ProductionSingleKind toProductionSingleKind(String internal_value) throws WTInvalidParameterException {
        return (ProductionSingleKind)toEnumeratedType(internal_value, _valueSet());
    }

    public static ProductionSingleKind getProductionSingleKindDefault() {
        return (ProductionSingleKind)defaultEnumeratedType(_valueSet());
    }

    public static ProductionSingleKind[] getProductionSingleKindSet() {
        ProductionSingleKind[] set = new ProductionSingleKind[_valueSet().length];
        System.arraycopy(valueSet, 0, set, 0, valueSet.length);
        return set;
    }

    public EnumeratedType[] getValueSet() {
        return getProductionSingleKindSet();
    }

    protected EnumeratedType[] valueSet() {
        return _valueSet();
    }

    protected EnumeratedType[] getLocaleSet(Locale locale) {
        EnumeratedType[] request = null;
        if (localeSets == null) {
            localeSets = new Hashtable();
        } else {
            request = (EnumeratedType[])((EnumeratedType[])localeSets.get(locale));
        }

        if (request == null) {
            try {
                request = initializeLocaleSet(locale);
            } catch (Throwable var4) {
                ;
            }

            localeSets.put(locale, request);
        }

        return request;
    }

    static EnumeratedType[] initializeLocaleSet(Locale locale) throws Throwable {
        return instantiateSet(ProductionSingleKind.class.getMethod("newProductionSingleKind", Integer.TYPE), "ext.corilead.prod.single.ProductionSingleKindRB", locale);
    }
}
