var totalRecord, currentPage;

$(function () {
    toPage(1);
});

/*查找哪一页*/
function toPage(pn) {
    $.ajax({
        url: "./allEmpsAjax",
        type: "get",
        data: "pn=" + pn,
        success: function (result) {
            buildTable(result);
            buildPageInfo(result);
            buildNav(result)
        }
    });
}

/*构建表格*/
function buildTable(result) {
    $("#empTable tbody").empty();
    var emps = result.maps.pageInfo.list;
    $.each(emps, function (index, item) {
        var checkBoxTd=$("<td>").append("<input type='checkbox' class='check_item'/>");
        var empNameTd = $("<td>").append(item.empName);
        var genderTd = $("<td>").append(item.empGender == 'M' ? "男" : "女");
        var emailTd = $("<td>").append(item.email);
        var deptNameTd = $("<td>").append(item.dept.deptName);
        var editBtn = $("<button>").addClass("btn-primary btn-xs editBtn").append($("<span>"))
            .addClass("glyphicon glyphicon-pencil").append("编辑");
        editBtn.attr("edit-id", item.empId);
        var delBtn = $("<button>").addClass("btn-danger btn-xs delBtn").append($("<span>"))
            .addClass("glyphicon glyphicon-trash").append("删除");
        delBtn.attr("del-id", item.empId);
        var empBtn = $("<td>").append(editBtn).append(" ").append(delBtn);
        $("<tr>").attr("delId",item.empId).append(checkBoxTd).append(empNameTd)
            .append(genderTd)
            .append(emailTd)
            .append(deptNameTd)
            .append(empBtn).appendTo($("#empTable tbody"));
    })


}

/*构建页面信息*/
function buildPageInfo(result) {
    $("#EmpPageInfo").empty();
    var pageInfo = result.maps.pageInfo;
    $("#EmpPageInfo").append("当前第" + pageInfo.pageNum + "页," +
        "共" + pageInfo.pages + "页，共" + pageInfo.total + "条记录");
    currentPage = pageInfo.pageNum;
    totalRecord = pageInfo.pages;
}

/*构建分页*/
function buildNav(result) {
    $("#empPageNav").empty();
    var pageInfo = result.maps.pageInfo;
    var prePage = $("<li>").append($("<a>").append("&laquo;"));
    if (!pageInfo.hasPreviousPage) {
        prePage.addClass("disabled");
    } else {
        prePage.click(function () {
            toPage(pageInfo.pageNum - 1);
        });
    }
    $("#empPageNav").append(prePage);
    var nextPage = $("<li>").append($("<a>").append("&raquo;"));
    if (!pageInfo.hasNextPage) {
        nextPage.addClass("disabled");
    } else {
        nextPage.click(function () {
            toPage(pageInfo.pageNum + 1);
        });
    }
    $.each(pageInfo.navigatepageNums, function (index, item) {
        var numPage = $("<li>").append($("<a>").append(item));
        if (pageInfo.pageNum == item) {
            numPage.addClass("active");
        }
        numPage.click(function () {
            toPage(item);
        });
        $("#empPageNav").append(numPage);

    });
    $("#empPageNav").append(nextPage);
}

/*获取部门*/
function getDept() {
    $.ajax({
        type: "get",
        url: "./getAllDept",
        success: function (result) {
            var depts = result.maps.depts;
            $.each(depts, function (index, item) {
                $("<option>").append(item.deptName).attr("value", item.deptId).appendTo($("#dept_Name"));
            })

        }
    });
}

/*清空表单*/
function form_empty() {
    $("#emp_Name").val("");
    $("#emp_Email").val("");
    $("#dept_Name").empty();
    $("#empNameStatus").removeClass("has-error has-success");
    $("#empEmailStatus").removeClass("has-error has-success");


}
/*添加员工*/
function saveEmp() {
    $.ajax({
        url: "./saveEmp",
        type: "get",
        data: $("#empForm").serialize(),
        success: function (result) {
            if (result.code == 100) {
                $("#empAddModal").modal('hide');
                toPage(totalRecord);
            }

        }
    });
}

/*验证表单*/
function validateForm() {
    var empName = $("#emp_Name").val();
    var validateName = /(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5}$)/;
    if (!validateName.test(empName)) {
        $("#empNameStatus").addClass("has-error");
        $("#empNameStatus div").append($("<span>").addClass("help-block").append("名字不合法"));
        return false;
    } else {
        $("#empNameStatus").addClass("has-success");
    }

    var empEmail = $("#emp_Email").val();
    var validateEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
    if (!validateEmail.test(empEmail)) {
        $("#empEmailStatus").addClass("has-error");
        $("#empEmailStatus div").append($("<span>").addClass("help-block").append("邮箱不合法"));
        return false;
    } else {
        $("#empEmailStatus").addClass("has-success");
    }

}

/*检查名字是否重复*/
function checkEmpName() {
    var empName = $("#emp_Name").val();
    $.ajax({
        type: "get",
        url: "./checkName/emp/" + empName,
        success: function (result) {
            if (result.code == 200) {
                $("#empNameStatus").addClass("has-error");
                $("#empNameStatus div").append($("<span>").addClass("help-block").append("用户名已经注册"));
                return false;
            }

        }
    })

}

function getEmp(id) {
    $.ajax({
        url: "./getEmp/" + id,
        type: "get",
        success: function (result) {
            var empInfo = result.maps.empInfo;
            $("#upEmp_Name").val(empInfo.empName);
            $("#upEmp_Email").val(empInfo.email);
            $("#upEmp_Gender label input").val([empInfo.empGender]);
            $("#upDept_Name").val(empInfo.deptId);
            $("#editBtn").attr("editId", empInfo.empId);

        }
    });

}

function updateEmp() {
    $.ajax({
        url: "./saveEmp/emp/" + $("#editBtn").attr("editId"),
        type: "post",
        data: $("#upEmpForm").serialize(),
        success: function (result) {
            $('#upEmpModal').modal('hide');
            toPage(currentPage);

        }
    });
}

function deleteEmp(empId) {
    $.ajax({
       type:"delete",
       url:"./saveEmp/emp/"+empId,
       success:function (result) {
           toPage(currentPage);
       }
    });

}



