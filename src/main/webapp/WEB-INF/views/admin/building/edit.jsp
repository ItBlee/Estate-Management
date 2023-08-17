<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="apiUrl" value="/api/building"/>
<html>
<head>
    <title>
        <c:if test="${empty model.id}">
            Thêm tòa nhà
        </c:if>
        <c:if test="${not empty model.id}">
            Chỉnh sửa thông tin tòa nhà
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
                            Thêm tòa nhà
                        </c:if>
                        <c:if test="${not empty model.id}">
                            Chỉnh sửa thông tin tòa nhà
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
                            <label for="name">Tên tòa nhà</label>
                        </div>
                        <input type="text" id="name" name="name" value="${model.name}" class="col-xs-9"
                            <security:authorize access="!hasRole('MANAGER')">disabled</security:authorize>
                        >
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="districtCode">Quận</label><br>
                        </div>
                        <select class="form-select form-select-sm" id="districtCode" name="districtCode">
                            <option value="">---Chọn quận---</option>
                            <c:forEach var="district" items="${districts}">
                            <option value="${district.key}"
                                    <c:if test="${district.key.equals(model.districtCode)}">selected</c:if>
                                >${district.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="ward">Phường</label>
                        </div>
                        <input type="text" id="ward" name="ward" value="${model.ward}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="street">Đường</label>
                        </div>
                        <input type="text" id="street" name="street" value="${model.street}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="structure">Kết cấu</label>
                        </div>
                        <input type="text" id="structure" name="structure" value="${model.structure}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="numberOfBasement">Số tầng hầm</label>
                        </div>
                        <input type="number" id="numberOfBasement" name="numberOfBasement" value="${model.numberOfBasement}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="floorArea">Diện tích sàn</label>
                        </div>
                        <input type="number" id="floorArea" name="floorArea" value="${model.floorArea}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="direction">Hướng</label>
                        </div>
                        <input type="text" id="direction" name="direction" value="${model.direction}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="level">Hạng</label>
                        </div>
                        <input type="text" id="level" name="level" value="${model.level}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="rentAreas">Diện tích thuê</label>
                        </div>
                        <input type="text" id="rentAreas" name="rentAreas" value="${model.rentAreas}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="rentAreasDescription">Mô tả diện tích</label>
                        </div>
                        <textarea class="col-xs-9" id="rentAreasDescription" name="rentAreasDescription" value="${model.rentAreasDescription}" rows="7"></textarea>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="rentPrice">Giá thuê</label>
                        </div>
                        <input type="number" id="rentPrice" name="rentPrice" value="${model.rentPrice}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="rentPriceDescription">Mô tả giá</label>
                        </div>
                        <input type="text" id="rentPriceDescription" name="rentPriceDescription" value="${model.rentPriceDescription}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="serviceFee">Phí dịch vụ</label>
                        </div>
                        <input type="text" id="serviceFee" name="serviceFee" value="${model.serviceFee}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="carFee">Phí ô tô</label>
                        </div>
                        <input type="text" id="carFee" name="carFee" value="${model.carFee}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="motorbikeFee">Phí mô tô</label>
                        </div>
                        <input type="text" id="motorbikeFee" name="motorbikeFee" value="${model.motorbikeFee}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="overtimeFee">Phí ngoài giờ</label>
                        </div>
                        <input type="text" id="overtimeFee" name="overtimeFee" value="${model.overtimeFee}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="waterFee">Tiền nước</label>
                        </div>
                        <input type="text" id="waterFee" name="waterFee" value="${model.waterFee}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="electricityFee">Tiền điện</label>
                        </div>
                        <input type="text" id="electricityFee" name="electricityFee" value="${model.electricityFee}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="deposit">Đặt cọc</label>
                        </div>
                        <input type="text" id="deposit" name="deposit" value="${model.deposit}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="payment">Thanh toán</label>
                        </div>
                        <input type="text" id="payment" name="payment" value="${model.payment}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="rentTime">Thời hạn thuê</label>
                        </div>
                        <input type="text" id="rentTime" name="rentTime" value="${model.rentTime}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="decorationTime">Thời gian trang trí</label>
                        </div>
                        <input type="text" id="decorationTime" name="decorationTime" value="${model.decorationTime}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="managerName">Tên quản lý</label>
                        </div>
                        <input type="text" id="managerName" name="managerName" value="${model.managerName}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="managerPhone">SĐT quản lý</label>
                        </div>
                        <input type="text" id="managerPhone" name="managerPhone" value="${model.managerPhone}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="brokerageFee">Phí môi giới</label>
                        </div>
                        <input type="number" id="brokerageFee" name="brokerageFee" value="${model.brokerageFee}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label>Loại sản phẩm</label>
                        </div>
                        <div class="checkbox">
                            <c:forEach var="rentType" items="${rentTypes}">
                                <label>
                                    <input id="rentTypeCodes" name="rentTypeCodes" value="${rentType.key}" type="checkbox" class="ace"
                                            <c:if test="${model.rentTypeCodes.contains(rentType.key)}">checked</c:if>/>
                                    <span class="lbl"> ${rentType.value}</span>
                                </label>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="note">Ghi chú</label>
                        </div>
                        <textarea class="col-xs-9" id="note" name="note" value="${model.note}" rows="5"></textarea>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="linkOfBuilding">Link sản phẩm</label>
                        </div>
                        <input type="text" id="linkOfBuilding" name="linkOfBuilding" value="${model.linkOfBuilding}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="map">Bản đồ</label>
                        </div>
                        <input type="text" id="map" name="map" value="${model.map}" class="col-xs-9">
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">
                            <label for="map">Hình đại diện</label>
                        </div>
                        <div class="col-xs-3">
                            <label class="ace-file-input">
                                <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg">
                                <span class="ace-file-container" data-title="Choose">
                                <span class="ace-file-name">
                                    <i class=" ace-icon fa fa-upload"></i>
                                    <p id="avatarName"> ${model.avatarName} </p>
                                </span>
                            </span>
                                <a class="remove" href="#">
                                    <i class=" ace-icon fa fa-times"></i>
                                </a>
                            </label>
                            <div class="col-sm-12">
                                <c:if test="${not empty model.avatarPath}">
                                    <c:set var="imagePath" value="/repository/${model.avatarPath}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-bottom: 50px" alt="avatar">
                                </c:if>
                                <c:if test="${empty model.avatarPath}">
                                    <img src="/admin/img/default.png" id="viewImage" width="300px" height="300px" alt="avatar">
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right message-info"></label>
                        <button type="button" class="btn btn-primary" id="btnAddBuilding">
                            <c:if test="${empty model.id}">
                                Thêm tòa nhà
                            </c:if>
                            <c:if test="${not empty model.id}">
                                Cập nhật tòa nhà
                            </c:if>
                        </button>
                        <%--<button type="button" class="btn btn-danger">Hủy</button>--%>
                        <img src="/admin/img/loading.gif" style="display: none; height: 100px" id="loading_image" alt="loading...">
                    </div>
                </form>

                <!-- PAGE-CONTENT-END -->
            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

<script>
    let imageBase64 = '';
    let imageName = $("#avatarName").text().trim();

    $("#avatar").change(function(){
        imageName = this.files[0].name;
        $("#avatarName").text(imageName);
        readURL(this);
    });

    function readURL(input) {
        const url = input.value;
        const ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();
        if (input.files && input.files[0] && (ext === "gif" || ext === "png" || ext === "jpeg" || ext === "jpg")) {
            const reader = new FileReader();
            reader.onload = function (e) {
                imageBase64 = e.target.result;
                $('#viewImage').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        } else {
            $('#viewImage').attr('src', '/admin/img/default.png');
        }
    }

    /*$('#btnAddBuilding').click(function (e) {
        e.preventDefault();
        $('#loading_image').show();
        const data = new FormData();
        const rentTypeCodes = [];
        const formData = $('#formEdit').serializeArray();
        $.each(formData, function (index, v) {
            if (v.name === 'rentTypeCodes')
                rentTypeCodes.push(v.value);
            else data.append(v.name, v.value);
        });
        data.append('rentTypeCodes', rentTypeCodes.join(","));
        const buildingId = $('#id').val();
        if (buildingId === '')
            addBuilding(data);
        else updateBuilding(buildingId, data);
    });*/

    $('#btnAddBuilding').click(function (e) {
        e.preventDefault();
        $('#loading_image').show();
        const form = $('#formEdit')[0];
        const formData = new FormData(form);
        if (imageName !== '')
            formData.set('avatarName', imageName);
        if (imageBase64 !== '')
            formData.set('avatar', imageBase64);
        else formData.delete('avatar');

        const data = {};
        const rentTypeCodes = [];
        formData.forEach(function(value, key){
            if (key === 'rentTypeCodes')
                rentTypeCodes.push(value);
            else data[key] = value;
        });
        data['rentTypeCodes'] = rentTypeCodes.join(",");

        /*for (let pair of formData.entries()) {
            console.log(pair[0]+ ', ' + pair[1]);
        }
        console.log(data);*/

        const buildingId = data['id'];
        if (buildingId === '')
            addBuilding(data);
        else updateBuilding(buildingId, data);
    });

    function addBuilding(data) {
        $.ajax({
            type: 'POST',
            url: '${apiUrl}/',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                showAlertThen("Thành công", "Đã thêm mới tòa nhà !", "success", function () {
                    window.location.href = "<c:url value='/admin/building-edit-" + response.id + "'/>";
                })
            },
            error: function (response) {
                showAlert("Lỗi", response, "error");
            }
        });
        $('#loading_image').hide();
    }

    function updateBuilding(id, data) {
        $.ajax({
            type: 'PUT',
            url: '${apiUrl}/' + id,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                showAlertWithReload("Thành công", "Đã cập nhật tòa nhà !", "success");
            },
            error: function (response) {
                showAlert("Lỗi", response, "error");
            }
        });
        $('#loading_image').hide();
    }

</script>

</body>
</html>
