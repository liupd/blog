<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="import.jsp" %>
<div id="modalDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="modalLabel"></h4>
			</div>
			<div class="modal-body" style="padding:0px;margin:0px;height:320px;">
				<iframe id="modalFrame" frameborder="0" src="" style="width:100%;height:100%;margin:0px;padding:0px;"></iframe>
			</div>
		</div>
	</div>
</div>