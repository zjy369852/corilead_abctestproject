<%@taglib prefix="jca" uri="http://www.ptc.com/windchill/taglib/components"%>
<%@taglib prefix="attachments" uri="http://www.ptc.com/windchill/taglib/attachments"%>
<%@include file="/netmarkets/jsp/components/beginWizard.jspf"%>
<%@include file="/netmarkets/jsp/components/includeWizBean.jspf"%>
<jca:initializeItem baseTypeName="corilead.prod.single.ProductionSingle" operation="${createBean.edit}" attributePopulatorClass="com.ptc.core.components.forms.DefaultAttributePopulator"/>
<jca:wizard>
    <jca:wizardStep action="ProductionAttributesWizStep" type="ProductionSingle"/>
    <jca:wizardStep action="attachments_step" type="attachments"/>
</jca:wizard>
<attachments:fileSelectionAndUploadApplet/>
<%@include file="/netmarkets/jsp/util/end.jspf"%>