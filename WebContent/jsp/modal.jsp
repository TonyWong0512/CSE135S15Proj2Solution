<div class="modal fade" id="category-<%= request.getParameter("id") %>" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel"><%=request.getParameter("title") %></h4>
            </div>
            <div class="modal-body">
                <%= request.getParameter("data") %>
            </div>
        </div>
    </div>
</div>