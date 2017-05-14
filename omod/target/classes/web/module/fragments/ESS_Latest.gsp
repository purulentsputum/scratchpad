<style>
	table {
		border: 1px solid black;
	}
</style>



<script>
	jq(document).ready(function(\$) {
		jq(".clickable-row").click(function() {
			window.document.location=\$(this).data("href");
		});
	});

</script>


<div>
	<% if (ESSresult.length()>1) { %>
		<table>
			<tr class = 'clickable-row' data-href= '${ ui.pageLink("scratchpad", "ESS_List", [patientId: patient.id]) }' >
				<td style="width:120px"> ${ ESSdate.format("dd-MMM-yyyy") } </td>
				<td style="width:200px"> Epworth Sleepiness Scale </td>
				<td style="width:200px"> ${ ESSresult } </td>
			</tr>
		</table>
	<% } %>
</div>