<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="submitUrl" value="/admin/customer-list"/>
<c:url var="addUrl" value="/admin/customer-edit"/>
<c:url var="editUrl" value="/admin/customer-edit-"/>
<c:url var="customerApiUrl" value="/api/customer"/>
<c:url var="staffApiUrl" value="/api/user/staffs"/>
<html>
<head>
    <title>Danh sách khách hàng</title>
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
                        <a href="<c:url value="/admin/home"/>">
                            Trang chủ
                        </a>
                    </li>
                    <li class="active">Danh sách khách hàng</li>
                </ul><!-- /.breadcrumb -->

            </div>

            <div class="page-content">
                <!-- PAGE-CONTENT-BEGIN -->
                <div class="row">
                    <div class="col-xs-12">

                        <div class="widget-box">
                            <div class="widget-header">
                                <h4 class="widget-title">Tìm kiếm</h4>

                                <div class="widget-toolbar">
                                    <a href="#" data-action="collapse">
                                        <i class="ace-icon fa fa-chevron-up"></i>
                                    </a>
                                </div>
                            </div>

                            <div class="widget-body">
                                <div class="widget-main">
                                    <form:form modelAttribute="model" action="${submitUrl}" method="GET" id="formSearch" cssClass="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="fullName"><b>Tên khách hàng</b></label>
                                                        <form:input path="fullName" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="phone"><b>Di động</b></label>
                                                        <form:input path="phone" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="email"><b>Email</b></label>
                                                        <form:input path="email" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <security:authorize access="hasRole('MANAGER')">
                                            <div class="form-group">
                                                <div class="col-xs-12">
                                                    <div class="col-sm-3">
                                                        <div>
                                                            <label for="staffId"><b>Nhân viên phụ trách</b></label>
                                                            <form:select path="staffId" id="staffId" cssClass="form-control">
                                                                <form:option value="" label="--- Chọn nhân viên phụ trách ---"/>
                                                                <form:options items="${staffs}" itemLabel="fullName" itemValue="id"/>
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </security:authorize>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                &nbsp;
                                                <button class="btn btn-success" id="btnSearch">Tìm kiếm <i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                                            </div>
                                        </div>

                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <security:authorize access="hasRole('MANAGER')">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="pull-right">
                                <a class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Thêm khách hàng"
                                   href='${addUrl}'>
                                    <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                </a>
                                <a class="btn btn-white btn-danger btn-bold" data-toggle="tooltip" title="Xóa khách hàng" id="btnDeleteCustomer">
                                    <i class="fa fa-trash-o" aria-hidden="true"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </security:authorize>

                <br><br>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <display:table name="pages.source" cellspacing="0" cellpadding="0"
                                           requestURI="${submitUrl}" partialList="true" sort="external"
                                           size="${pages.totalItems}" defaultsort="3" defaultorder="ascending"
                                           id="tableList" pagesize="${pages.pageSize}" export="false"
                                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                           style="margin: 3em 0 1.5em;">
                                <security:authorize access="hasRole('MANAGER')">
                                    <display:column title="<fieldset class='form-group'>
												            <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                    headerClass="center select-cell">
                                        <fieldset>
                                            <input type="checkbox" name="checkList" value="${tableList.id}" id="checkbox_${tableList.id}" class="check-box-element"/>
                                        </fieldset>
                                    </display:column>
                                </security:authorize>
                                <display:column headerClass="text-left" property="fullName" title="Tên"/>
                                <display:column headerClass="text-left" property="staffs" title="Nhân viên quản lý"/>
                                <display:column headerClass="text-left" property="phone" title="Di động"/>
                                <display:column headerClass="text-left" property="email" title="Email"/>
                                <display:column headerClass="text-left" property="demand" title="Nhu cầu"/>
                                <display:column headerClass="text-left" property="createdBy" title="Người nhập"/>
                                <display:column headerClass="text-left" property="createdDate" title="Ngày nhập" format="{0,date,dd/MM/yyyy}"/>
                                <display:column headerClass="text-left" title="Tình trạng">
                                    <c:if test="${tableList.status == 0}">
                                        <p class="text-danger">Vô hiệu hóa</p>
                                    </c:if>
                                    <c:if test="${tableList.status == 1}">
                                        <p class="text-primary">Đang xử lý</p>
                                    </c:if>
                                    <c:if test="${tableList.status == 2}">
                                        <p class="text-success">Hoàn tất</p>
                                    </c:if>
                                </display:column>
                                <display:column headerClass="col-actions" title="Thao tác">
                                    <div class="hidden-sm hidden-xs">
                                        <%--<a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="Xem khách hàng">
                                            <i class="fa fa-eye" aria-hidden="true"></i>
                                        </a>--%>

                                        <a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="Sửa khách hàng"
                                           href='${editUrl}${tableList.id}'>
                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                        </a>

                                        <security:authorize access="hasRole('MANAGER')">
                                            <a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="Giao khách hàng"
                                               onclick="assignCustomer(${tableList.id})">
                                                <i class="fa fa-bars" aria-hidden="true"></i>
                                            </a>
                                        </security:authorize>
                                        <c:if test="${tableList.status == 1}">
                                            <a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="Hoàn tất"
                                               onclick="confirmChangeStatus(${tableList.id})">
                                                <i class="fa fa-check" aria-hidden="true"></i>
                                            </a>
                                        </c:if>
                                        <security:authorize access="hasRole('MANAGER')">
                                            <c:if test="${tableList.status == 2}">
                                                <a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="Hoạt động"
                                                   onclick="changeStatus(${tableList.id})">
                                                    <i class="fa fa-refresh" aria-hidden="true"></i>
                                                </a>
                                            </c:if>
                                        </security:authorize>

                                    </div>
                                </display:column>
                            </display:table>
                        </div>

                    </div><!-- /.span -->
                </div>
                <!-- PAGE-CONTENT-END -->

            </div>
            <!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

<security:authorize access="hasRole('MANAGER')">
    <!-- Modal -->
    <div class="modal fade" id="assignmentCustomerModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Danh sách nhân viên</h4>
                </div>
                <div class="modal-body">
                    <table id="staffList" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">Chọn nhân viên</th>
                            <th>Tên nhân viên</th>
                        </tr>
                        </thead>

                        <tbody>
                        </tbody>
                    </table>
                    <input type="hidden" name="customerId" id="customerId" value="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="btnAssignCustomer">Giao khách hàng</button>
                    <button type="button" class="btn btn-warning" data-dismiss="modal">Đóng</button>
                </div>
            </div>

        </div>
    </div>
</security:authorize>

<script>

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#formSearch').submit();
    })

    function confirmChangeStatus(customerId) {
        showConfirm("hoàn tất", function () {
            changeStatus(customerId);
        })
    }

    function changeStatus(customerId) {
        $.ajax({
            type: 'PUT',
            url: '${customerApiUrl}/status/' + customerId,
            contentType: 'application/json',
            success: function (response) {
                showAlertWithReload("Hoàn tất", "Đã cập nhật trạng thái khách hàng", "success");
            },
            error: function (response) {
                showAlert("Lỗi", response, "error");
            }
        });
    }

    <security:authorize access="hasRole('MANAGER')">
        function assignCustomer(customerId) {
            $('#customerId').val(customerId);
            loadStaffOfCustomer(customerId);
        }

        function openModelAssignmentCustomer() {
            $('#assignmentCustomerModal').modal();
        }

        function loadStaffOfCustomer(customerId) {
            $.ajax({
                type: 'GET',
                url: '${staffApiUrl}',
                data: { customerId: customerId },
                dataType: 'json',
                success: function (response) {
                    renderModelBody(response);
                    openModelAssignmentCustomer();
                },
                error: function (response) {
                    showAlert("Lỗi", response, "error");
                }
            });
        }

        function renderModelBody(response) {
            let row = '';
            $.each(response, function (index, item) {
                row += '<tr>';
                row += '<td class="center">';
                row += '<label class="pos-rel">';
                row += '<input type="checkbox" class="ace" value="' + item.id + '" id="checkbox_' + item.id + '" ' + item.checked + '>';
                row += '<span class="lbl"></span>';
                row += '</label>';
                row += '</td>';
                row += '<td>' + item.fullName + '</td>';
                row += '</tr>';
            })
            $('#staffList tbody').html(row);
        }

        $('#btnAssignCustomer').click(function (e){
            e.preventDefault();
            const customerId = $('#customerId').val();
            let data = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            assignStaff(customerId, data);
        });

        function assignStaff(customerId, data) {
            $.ajax({
                type: 'PUT',
                url: '${customerApiUrl}/assign/' + customerId,
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: 'application/json',
                success: function (response) {
                    let title = "Hoàn tất";
                    let action = "Đã giao khách hàng cho nhân viên";
                    if (data.length === 0) {
                        action = "Đã hủy giao khách hàng";
                    }
                    showAlertWithReload(title, action, "success");
                },
                error: function (response) {
                    showAlert("Lỗi", response, "error");
                }
            });
        }

        $('#checkAll').change(function () {
            $('tbody tr td input[type="checkbox"]').prop('checked', $(this).prop('checked'));
        });

        $('#btnDeleteCustomer').click(function (e) {
            e.preventDefault();
            const data = $('#tableList')
                .find('tbody input[type=checkbox]:checked')
                .map(function () {
                    return $(this).val();
                }).get();
            if (data.length === 0)
                showAlert("Thông báo", "Hãy chọn khách hàng !", "info");
            else showConfirm('vô hiệu hóa', function () {
                deleteCustomer(data);
            });
        });

        function deleteCustomer(data) {
            $.ajax({
                type: 'DELETE',
                url: '${customerApiUrl}/',
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: 'application/json',
                success: function (response) {
                    showAlertWithReload("Hoàn tất", "Đã vô hiệu hóa khách hàng", "success");
                },
                error: function (response) {
                    showAlert("Lỗi", response, "error");
                }
            });
        }
    </security:authorize>

</script>

</body>
</html>
