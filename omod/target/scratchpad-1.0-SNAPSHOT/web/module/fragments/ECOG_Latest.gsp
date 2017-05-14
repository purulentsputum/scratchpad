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
	<table>
		<tr class = 'clickable-row' data-href= '${ ui.pageLink("scratchpad", "ECOGList", [patientId: patient.id]) }' >
			<td style="width:120px"> ${ ECOG.get(1).format("dd-MMM-yyyy") } </td>
			<td style="width:200px"> ${ ECOG.get(2) } </td>
			<td style="width:200px"> ${ ECOG.get(3) } </td>
		</tr>
	</table>
</div>