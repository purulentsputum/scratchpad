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

</style>

<script>
	jq(document).ready(function(\$) {
		jq(".clickable-row").click(function() {
			javascript:publish("ECOGItemViewTable.selectedUUID", \$(this).data("uuid"));
		});
	});
</script>


<br/>
<table id="ECOGItemViewTable">
	
	<tr>
		<th width="120">Date</th>
		<th width="350">ECOG Status</th>
	</tr>
	<% ECOG.each { ObsList -> %>
		<tr class = 'clickable-row' data-uuid= '${ ObsList.getUuid() }'>
			<td> ${ ObsList.getObsDatetime().format("dd-MMM-yyyy") } </td>
			<td> ${ ENUM.get(ObsList.getValueNumeric().intValue()).toString(false) }</td>
		</tr>
	<% } %>
</table>