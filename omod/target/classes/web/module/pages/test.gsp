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
		   label: "Scratch Pad"
	   }
	   ];

</script>
	
<div>
	
	${ ui.includeFragment("coreapps", "patientHeader", [ patient: patient ]) }

	<div id="bodyText">

		Hello, ${ patient.getPerson().getPersonName() }
		
		<br/>
		${ ui.includeFragment("uiframework","helloUser") }
		<br/>
		

		
		
		${ ui.includeFragment("scratchpad","ECOG_Latest", [patient: patient]) }
		
		${ ui.includeFragment("scratchpad","ESS_Latest", [patient: patient]) }
		<br/>
		
	</div>
	
	<a href=' ${ ui.pageLink("scratchpad","ConceptUpdate", [ patient: patient ]) }'> Update Concepts </a>
	
	
</div>