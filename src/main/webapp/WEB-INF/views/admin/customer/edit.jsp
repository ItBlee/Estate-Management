<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="apiUrl" value="/api/customer"/>
<html>
<head>
    <title>
        <c:if test="${empty model.id}">
            Thêm khách hàng
        </c:if>
        <c:if test="${not empty model.id}">
            Chỉnh sửa thông tin khách hàng
        </c:if>
    </title>
</head>
<body>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="<c:url value="/admin/home"/>">Trang chủ</a>
                    </li>
                    <li class="active">
                        <c:if test="${empty model.id}">
                            Thêm khách hàng
                        </c:if>
                        <c:if test="${not empty model.id}">
                            Chỉnh sửa thông tin khách hàng
                        </c:if>
                    </li>
                </ul><!-- /.breadcrumb -->

            </div>

            <div class="page-content">
                <!-- PAGE-CONTENT-BEGIN -->
                <form class="form-horizontal" role="form" id="formEdit" name="formEdit" enctype="multipart/form-data">
                    <input type="hidden" id="id" name="id" value="${model.id}">
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="fullName">Tên khách hàng</label>
                        </div>
                        <input type="text" id="fullName" name="fullName" value="${model.fullName}" class="col-xs-9"
                               <security:authorize access="!hasRole('MANAGER')">disabled</security:authorize>
                        >
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="phone">Số điện thoại</label>
                        </div>
                        <input type="text" id="phone" name="phone" value="${model.phone}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="email">Email</label>
                        </div>
                        <input type="text" id="email" name="email" value="${model.email}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="company">Tên công ty</label>
                        </div>
                        <input type="text" id="company" name="company" value="${model.company}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="demand">Nhu cầu</label>
                        </div>
                        <input type="text" id="demand" name="demand" value="${model.demand}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="note">Ghi chú</label>
                        </div>
                        <input type="text" id="note" name="note" value="${model.note}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right message-info"></label>
                        <button type="button" class="btn btn-primary" id="btnAddCustomer">
                            <c:if test="${empty model.id}">
                                Thêm khách hàng
                            </c:if>
                            <c:if test="${not empty model.id}">
                                Cập nhật khách hàng
                            </c:if>
                        </button>
                        <button type="button" class="btn btn-danger">Hủy</button>
                        <img src="/admin/img/loading.gif" style="display: none; height: 100px" id="loading_image" alt="loading...">
                    </div>
                </form>

                <c:if test="${not empty model.id}">
                    <br><br><br>

                    <c:forEach var="transaction" items="${model.transactionMap}">
                        <div class="row">
                            <h2 class="col-xs-8 text-info">${transaction.key.name}</h2>
                            <c:if test="${model.status == 1}">
                                <div class="pull-right" style="margin: 15px 15px">
                                    <a role="button" class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Thêm giao dịch"
                                       onclick="addTransaction('${transaction.key.code}')">
                                        <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                    </a>
                                </div>
                            </c:if>

                        </div>
                        <hr style="margin-top:0">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="col-xs-1">Ngày tạo</th>
                                <th class="col-xs-2">Thực hiện</th>
                                <th class="col-xs-9">Ghi chú</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="item" items="${transaction.value}">
                                <tr>
                                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${item.createdDate}" /></td>
                                    <td>${item.createdBy}</td>
                                    <td>${item.note}</td>
                                </tr>
                            </c:forEach>
                            <c:if test="${model.status == 1}">
                                <tr>
                                    <td colspan="2"></td>
                                    <td>
                                        <input type="text" id="transaction${transaction.key.code}" name="transaction${transaction.key.code}" value="" class="col-xs-12">
                                    </td>
                                </tr>
                            </c:if>
                            </tbody>
                        </table>

                        <br>
                    </c:forEach>
                </c:if>

                <!-- PAGE-CONTENT-END -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

<script>

    $('#btnAddCustomer').click(function (e) {
        e.preventDefault();
        $('#loading_image').show();
        const form = $('#formEdit')[0];
        const formData = new FormData(form);

        const data = {};
        formData.forEach(function(value, key){
            data[key] = value;
        });

        /*for (let pair of formData.entries()) {
            console.log(pair[0]+ ', ' + pair[1]);
        }
        console.log(data);*/

        const customerId = data['id'];
        if (customerId === '')
            addCustomer(data);
        else updateCustomer(customerId, data);
    });

    function addCustomer(data) {
        $.ajax({
            type: 'POST',
            url: '${apiUrl}/',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                showAlertThen("Thành công", "Đã thêm mới khách hàng !", "success", function () {
                    window.location.href = "<c:url value='/admin/customer-edit-" + response.id + "'/>";
                })
            },
            error: function (response) {
                showAlert("Lỗi", response, "error");
            }
        });
        $('#loading_image').hide();
    }

    function updateCustomer(id, data) {
        $.ajax({
            type: 'PUT',
            url: '${apiUrl}/' + id,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                showAlertWithReload("Thành công", "Đã cập nhật khách hàng !", "success");
            },
            error: function (response) {
                showAlert("Lỗi", response, "error");
            }
        });
        $('#loading_image').hide();
    }

    function addTransaction(type) {
        const customerId = $('#id').val();
        const note = $('#transaction' + type).val();
        if (note.length === 0) {
            showAlert("Thông báo", "Hãy thêm ghi chú cho giao dịch", "info");
            return;
        }
        const data = {};
        data['customerId'] = customerId;
        data['typeCode'] = type;
        data['note'] = note;
        $.ajax({
            type: 'POST',
            url: '${apiUrl}/transaction/',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                showAlertWithReload("Thành công", "Đã thêm mới giao dịch !", "success");
            },
            error: function (response) {
                showAlert("Lỗi", response, "error");
            }
        });
    }

</script>

</body>
</html>
