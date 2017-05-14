<style type="text/css">
	table, th, td{
		text-align: center;
		padding: 5px;
	}
	
	th {
		background-color: #4CAF50;
		color: white;
	}
	
	tr:nth-child(even) {background-color: #F2F2F2}
	
	#dataVertical {
		width: 100%;
		overflow: auto;
	}
	#dataHorizontal {
		width: 100%;
		overflow: auto;
		max-height: 1000px;
	}
	#dataHorizontal th {
		min-width: 100px;
	}
	
</style>


<script type="text/javascript">
	ESSviewToggleButtonClicked = function() {
		
		if (jq('#ESSviewDataHorizontal').is(":visible")) {
			jq('#ESSviewDataHorizontal').hide();
			jq('#ESSviewDataVertical').show();
		} else {
			jq('#ESSviewDataHorizontal').show();
			jq('#ESSviewDataVertical').hide();
		};
	};

jq(document).ready(function() {
	jq(".ESSviewClickableElement").click(function() {
		var tempVar = jq(this).data("obsid");
		jq.publish("scratchpad/ESSViewFragment/selectedItem", [tempVar],"");
	});
});
</script>

<br/>
Cumulative Epworth Sleepiness Score Results
<br/>
	<label for="ESSviewToggleButton">Change Input Method: </label>
	<input type="button" onclick='ESSviewToggleButtonClicked()' id="ESSviewToggleButton" name="ESSviewToggleButton"  value='Rotate view'/>
<br/>
<div id="ESSviewDataHorizontal" style="display:none;overflow-x:scroll;overflow-y:scroll">
	<table id="ESSviewDataHorizontal-table">
		<tr>
			<th>id</th>
			<th>Date</th>
			<th>Location</th>
			<th>Acc No</th>
			<% for (loop1=0; loop1<obsList.sizeColumns(); loop1++) { %>
				<th> ${ conceptMap.get(loop1) } </th>
			<% } %>	
		</tr>
		<% 	for (loop1=0; loop1<obsDescriptorsMap.size(); loop1++) {  %>
			<tr class = 'ESSviewClickableElement' data-obsid = '${ obsDescriptorsMap.get(loop1).getObsId() }'>
				<td> ${ obsDescriptorsMap.get(loop1).getObsId() } </td>
				<td> ${ obsDescriptorsMap.get(loop1).getDate().format("dd-MMM-yyyy") } </td>
				<td> ${ obsDescriptorsMap.get(loop1).getLocation().getName() } </td>
				<td> ${ obsDescriptorsMap.get(loop1).getAccessionNumber() } </td>
				<% for (loop2=0; loop2<obsList.sizeColumns(); loop2++) { 
					if (obsList.get(loop1,loop2) != null) {%>
						<td> ${ obsList.get(loop1,loop2) }</td>
					<% } else { %>
						<td> </td>
					<% } %>
				<% } %>
			</tr>
		<% } %>
		
		
		

	</table>
</div>


<div id="ESSviewDataVertical"style="overflow-x:scroll;overflow-y:scroll" >
	<table id="ESSviewDataVertical-table">
		<tr>
			<th>Date</th>
		     <% for (loop1=0; loop1<obsDescriptorsMap.size(); loop1++) {  %>
					<th> ${ obsDescriptorsMap.get(loop1).getDate().format("dd-MMM-yyyy") } </th>
				<% } %>	
		</tr>
		<tr>
			<td>Location</td>
			<% for (loop1=0; loop1<obsDescriptorsMap.size(); loop1++) {  %>
				<td> ${ obsDescriptorsMap.get(loop1).getLocation().getName() } </td>
			<% } %>	
		</tr>
		<tr>
			<td>Acc No</td>
			<% for (loop1=0; loop1<obsDescriptorsMap.size(); loop1++) {  %>
				<td> ${ obsDescriptorsMap.get(loop1).getAccessionNumber() } </td>
			<% } %>	
		</tr>
		<% for (loop1=0; loop1<obsList.sizeColumns(); loop1++) { %>
			<tr>
			<td> ${ conceptMap.get(loop1) } </td>
				<% for (loop2=0; loop2<obsList.sizeRows(); loop2++) { %>
					<td class = 'ESSviewClickableElement' data-obsid= '${ obsDescriptorsMap.get(loop2).getObsId() }'>
						${ obsList.get(loop2,loop1) } 
					</td>
				<% } %>
			</tr>
		<% } %>
		<tr>
			<td> </td>
			<% for (loop1=0; loop1<obsDescriptorsMap.size(); loop1++) {  %>
				<td> <a href="publish('scratchpad/ESSViewFragment/selectedRow','${ obsDescriptorsMap.get(loop1).getObsId() }')"> edit </a> </td>
		<% } %>	
	</tr>
	</table>
</div>