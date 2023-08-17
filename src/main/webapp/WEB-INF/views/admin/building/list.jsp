<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="submitUrl" value="/admin/building-list"/>
<c:url var="addUrl" value="/admin/building-edit"/>
<c:url var="editUrl" value="/admin/building-edit-"/>
<c:url var="buildingApiUrl" value="/api/building"/>
<c:url var="staffApiUrl" value="/api/user/staffs"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                    <li class="active">Danh sách tòa nhà</li>
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
                                                <div class="col-sm-6">
                                                    <div>
                                                        <label for="name"><b>Tên tòa nhà</b></label>
                                                        <form:input path="name" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-6">
                                                    <div>
                                                        <label for="floorArea"><b>Diện tích sàn</b></label>
                                                        <input type="number" id="floorArea" name="floorArea" value="${model.floorArea}" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="districtCode"><b>Quận hiện có</b></label><br>
                                                        <form:select path="districtCode" cssClass="form-select form-select-sm">
                                                            <form:option value="" label="--- Chọn quận ---"/>
                                                            <form:options items="${districts}"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="ward"><b>Phường</b></label>
                                                        <form:input path="ward" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="street"><b>Đường</b></label>
                                                        <form:input path="street" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="numberOfBasement"><b>Số tầng hầm</b></label>
                                                        <input type="number" id="numberOfBasement" name="numberOfBasement" value="${model.numberOfBasement}" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="direction"><b>Hướng</b></label>
                                                        <form:input path="direction" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="level"><b>Hạng</b></label>
                                                        <form:input path="level" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-3">
                                                    <div>
                                                        <label for="rentAreaFrom"><b>Diện tích từ</b></label>
                                                        <input type="number" id="rentAreaFrom" name="rentAreaFrom" value="${model.rentAreaFrom}" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div>
                                                        <label for="rentAreaTo"><b>Diện tích đến</b></label>
                                                        <input type="number" id="rentAreaTo" name="rentAreaTo" value="${model.rentAreaTo}" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div>
                                                        <label for="rentPriceFrom"><b>Giá thuê từ</b></label>
                                                        <input type="number" id="rentPriceFrom" name="rentPriceFrom" value="${model.rentPriceFrom}" class="form-control">
                                                    </div>
                                                </div>
                                                <div class="col-sm-3">
                                                    <div>
                                                        <label for="rentPriceTo"><b>Giá thuê đến</b></label>
                                                        <input type="number" id="rentPriceTo" name="rentPriceTo" value="${model.rentPriceTo}" class="form-control">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="managerName"><b>Tên quản lý</b></label>
                                                        <form:input path="managerName" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                                <div class="col-sm-4">
                                                    <div>
                                                        <label for="managerPhone"><b>Điện thoại quản lý</b></label>
                                                        <form:input path="managerPhone" cssClass="form-control"/>
                                                    </div>
                                                </div>
                                                <security:authorize access="hasRole('MANAGER')">
                                                <div class="col-sm-3">
                                                    <div>
                                                        <label for="staffId"><b>Chọn nhân viên phụ trách</b></label>
                                                        <form:select path="staffId" id="staffId" cssClass="form-control">
                                                            <form:option value="" label="--- Chọn nhân viên phụ trách ---"/>
                                                            <form:options items="${staffs}" itemLabel="fullName" itemValue="id"/>
                                                        </form:select>
                                                    </div>
                                                </div>
                                                </security:authorize>

                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <%--<b><form:checkboxes path="rentTypeCodes" items="${rentTypes}" element="div class='checkbox-inline'" /></b>--%>
                                                <c:forEach var="rentType" items="${rentTypes}">
                                                    <div class="checkbox-inline">
                                                        <label>
                                                            <input id="rentTypeCodes" name="rentTypeCodes" value="${rentType.key}" type="checkbox" class="ace"
                                                                   <c:if test="${model.rentTypeCodes.contains(rentType.key)}">checked</c:if>/>
                                                            <span class="lbl"> ${rentType.value}</span>
                                                        </label>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>

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
                                <a class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Thêm tòa nhà"
                                   href='${addUrl}'>
                                    <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                </a>
                                <a class="btn btn-white btn-danger btn-bold" data-toggle="tooltip" title="Xóa tòa nhà" id="btnDeleteBuilding">
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
                                <display:column headerClass="text-left" property="createdDate" title="Ngày" format="{0,date,dd/MM/yyyy}"/>
                                <display:column headerClass="text-left" property="name" title="Tên sản phẩm"/>
                                <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                                <display:column headerClass="text-left" property="managerName" title="Tên quản lý"/>
                                <display:column headerClass="text-left" property="managerPhone" title="Số điện thoại"/>
                                <display:column headerClass="text-left" property="numberOfBasement" title="Số tầng hầm"/>
                                <display:column headerClass="text-left" property="floorArea" title="D.T sàn"/>
                                <display:column headerClass="text-left" property="rentAreas" title="D.T trống"/>
                                <display:column headerClass="text-left" property="rentPrice" title="Giá thuê"/>
                                <display:column headerClass="text-left" property="brokerageFee" title="Phí MG"/>
                                <display:column headerClass="col-actions" title="Thao tác">
                                    <div class="hidden-sm hidden-xs">
                                        <%--<a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="Xem tòa nhà">
                                            <i class="fa fa-eye" aria-hidden="true"></i>
                                        </a>--%>

                                        <a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="Sửa tòa nhà"
                                           href='${editUrl}${tableList.id}'>
                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                        </a>

                                        <security:authorize access="hasRole('MANAGER')">
                                            <a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="Giao tòa nhà"
                                               onclick="assignBuilding(${tableList.id})">
                                                <i class="fa fa-bars" aria-hidden="true"></i>
                                            </a>
                                        </security:authorize>

                                        <%--<a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="">
                                            <i class="fa fa-plus" aria-hidden="true"></i>
                                        </a>--%>
                                    </div>
                                </display:column>
                            </display:table>
                        </div>
                        <%--<table id="tableList" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="ace" id="selectAll">
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th>Ngày</th>
                                <th>Tên sản phẩm</th>
                                <th>Địa chỉ</th>
                                <th>Tên quản lý</th>
                                <th>Số điện thoại</th>
                                <th>Số tầng hầm</th>
                                <th>D.T sàn</th>
                                <th>D.T trống</th>
                                <th>Giá thuê</th>
                                <th>Phí MG</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="building" items="${buildings}">
                                <tr>
                                    <td class="center">
                                        <label class="pos-rel">
                                            <input type="checkbox" class="ace" value="${building.id}" id="checkbox_${building.id}">
                                            <span class="lbl"></span>
                                        </label>
                                    </td>

                                    <td><fmt:formatDate value="${building.createdDate}" pattern="dd/MM/yyyy" /></td>
                                    <td>${building.name}</td>
                                    <td>${building.address}</td>
                                    <td>${building.managerName}</td>
                                    <td>${building.managerPhone}</td>
                                    <td>${building.numberOfBasement}</td>
                                    <td>${building.floorArea}</td>
                                    <td>${building.rentAreas}</td>
                                    <td>${building.rentPrice}$</td>
                                    <td>${building.brokerageFee}</td>

                                    <td>
                                        <div class="hidden-sm hidden-xs">
                                            <a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="Xem tòa nhà">
                                                <i class="fa fa-eye" aria-hidden="true"></i>
                                            </a>

                                            <a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="Sửa tòa nhà"
                                               href='${editUrl}${building.id}'>
                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                            </a>

                                            <a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="Giao tòa nhà"
                                                    onclick="assignBuilding(${building.id})">
                                                <i class="fa fa-bars" aria-hidden="true"></i>
                                            </a>

                                            <a class="btn btn-xs btn-primary" role="button" data-toggle="tooltip" title="">
                                                <i class="fa fa-plus" aria-hidden="true"></i>
                                            </a>
                                        </div>

                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>--%>
                    </div><!-- /.span -->
                </div>
                <!-- PAGE-CONTENT-END -->

            </div>
            <!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

<security:authorize access="hasRole('MANAGER')">
    <!-- Modal -->
    <div class="modal fade" id="assignmentBuildingModal" role="dialog">
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
                    <input type="hidden" name="buildingId" id="buildingId" value="">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="btnAssignBuilding">Giao tòa nhà</button>
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

    <security:authorize access="hasRole('MANAGER')">
        function assignBuilding(buildingId) {
            $('#buildingId').val(buildingId);
            loadStaffOfBuilding(buildingId);
        }

        function openModelAssignmentBuilding() {
            $('#assignmentBuildingModal').modal();
        }

        function loadStaffOfBuilding(buildingId) {
            $.ajax({
                type: 'GET',
                url: '${staffApiUrl}',
                data: { buildingId: buildingId },
                dataType: 'json',
                success: function (response) {
                    renderModelBody(response);
                    openModelAssignmentBuilding();
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

        $('#btnAssignBuilding').click(function (e){
            e.preventDefault();
            const buildingId = $('#buildingId').val();
            let data = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            assignStaff(buildingId, data);
        });

        function assignStaff(buildingId, data) {
            $.ajax({
                type: 'PUT',
                url: '${buildingApiUrl}/assign/' + buildingId,
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: 'application/json',
                success: function (response) {
                    let title = "Hoàn tất";
                    let action = "Đã giao tòa nhà cho nhân viên";
                    if (data.length === 0) {
                        action = "Đã hủy giao tòa nhà";
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

        $('#btnDeleteBuilding').click(function (e) {
            e.preventDefault();
            const data = $('#tableList')
                .find('tbody input[type=checkbox]:checked')
                .map(function () {
                    return $(this).val();
                }).get();
            if (data.length === 0)
                showAlert("Thông báo", "Hãy chọn tòa nhà !", "info");
            else showConfirm('xóa', function () {
                deleteBuilding(data);
            });
        });

        function deleteBuilding(data) {
            $.ajax({
                type: 'DELETE',
                url: '${buildingApiUrl}/',
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: 'application/json',
                success: function (response) {
                    showAlertWithReload("Hoàn tất", "Đã xóa khách hàng", "success");
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
