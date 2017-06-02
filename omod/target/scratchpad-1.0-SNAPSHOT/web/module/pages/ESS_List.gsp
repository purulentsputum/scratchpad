<%
   ui.decorateWith("appui", "standardEmrPage")
   ui.includeJavascript("coreapps", "patientdashboard/patient.js")
   ui.includeJavascript("scratchpad", "jquery.subscribe.js")
   
//   if (!returnUrl) {
//  	 var  returnUrl = ui.pageLink("coreapps", "patientdashboard/patientDashboard", [patientId: patient.id])
//   }
   
   //def breadcrumbMiddle = breadcrumbOverride ?: """" [ {label: '${ returnLabel }', link: '${ returnUrl }'}] """
   
%>
<script type="text/javascript">
   var breadcrumbs = [
	   
	   { 
		   label: "${ ui.format(patient.personName) }",
		   link:  '/' + OPENMRS_CONTEXT_PATH + '/coreapps/clinicianfacing/patient.page?patientId=' + ${ patient.id }
	   },
	   ${ breadcrumbMiddle },
	   {
		   label: "Epworth Sleepiness Scale"
	   }
	   ];

</script>
	

<div>
	
	${ ui.includeFragment("coreapps", "patientHeader", [ patient: patient ]) }

	<div id="bodyText">
	
	<br/>

	${ ui.includeFragment("scratchpad","ESS_View", [patient: patient]) }

	<br/>
	${ ui.includeFragment("scratchpad","ESS_Add", [patient: patient]) }
	
	
	</div>
</div>
