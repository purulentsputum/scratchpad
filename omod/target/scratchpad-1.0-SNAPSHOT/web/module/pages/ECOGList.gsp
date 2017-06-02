<%
   ui.decorateWith("appui", "standardEmrPage")
   ui.includeJavascript("coreapps", "patientdashboard/patient.js")
   
//   if (!returnUrl) {
//  	 var  returnUrl = ui.pageLink("coreapps", "patientdashboard/patientDashboard", [patientId: patient.id])
//   }
   
%>
<script type="text/javascript">
   var breadcrumbs = [
	   { 
		   icon: "icon-home", 
		   link: '/' + OPENMRS_CONTEXT_PATH + '/index.htm' 
	   },
	   { 
		   label: "${ ui.format(patient) }",
		   link:  '/' + OPENMRS_CONTEXT_PATH + '/coreapps/clinicianfacing/patient.page?patientId=' + ${ patient.id }
	   },
	   {
		   label: "ECOG scale"
	   }
	   ];

</script>
	
<div>
	
	${ ui.includeFragment("coreapps", "patientHeader", [ patient: patient ]) }

	<div id="bodyText">

		
		below is the ecog table
		<br/>

		${ ui.includeFragment("scratchpad","ECOG_View", [patient: patient]) }
		
		<br/>
		
		<br/>
		${ ui.includeFragment("scratchpad","ECOG_Add", [patient: patient]) }
		<br/>
	</div>
	
	
</div>