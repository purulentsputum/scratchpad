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



<br/>
<table id="ECOGOrderViewTable">
	
	<tr>
		<th width="0">UUID</th>
		<th width="120">Date</th>
		<th width="150">Ordered By</th>

	</tr>
	<% ECOG.each { ObsList -> %>
		<tr>
			<td> ${ ObsList.getUuid() } </td>
			<td> ${ ObsList.getObsDatetime().format("dd-MMM-yyyy") } </td>
			<td> ${ ENUM.get(ObsList.getValueNumeric().intValue()).toString(false) }</td>
		</tr>
	<% } %>
</table>