<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
  <head>
    <title>AdminLTE 2 | Starter</title>
	<jsp:include page="common/common.jsp"></jsp:include>
	 
	<script type="text/javascript">
	$(function () {
		var data = [
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'},
			{'invid':'111', 'invdate':"1111", 'amount': '50', 'tax':'10', 'total': '100', 'note': 'note'}		
		];
		$("#list").jqGrid({
			data: data,
			datatype: "local",
			colNames: ["Inv No", "Date", "Amount", "Tax", "Total", "Notes"],
			colModel: [
				{ name: "invid", width: 55 },
				{ name: "invdate", width: 90 },
				{ name: "amount", width: 80, align: "right" },
				{ name: "tax", width: 80, align: "right" },
				{ name: "total", width: 80, align: "right" },
				{ name: "note", width: 150, sortable: false }
			],
			pager: "#pager",
			rowNum: 20,
			rowList: [10, 20, 30],
			sortname: "invid",
			sortorder: "desc",
			viewrecords: true,
			gridview: true,
			autoencode: true,
			caption: "My first grid",
			autowidth: true,
			height:500
		}); 
	}); 
	</script>
	
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <!--
  BODY TAG OPTIONS:
  =================
  Apply one or more of the following classes to get the
  desired effect
  |---------------------------------------------------------|
  | SKINS         | skin-blue                               |
  |               | skin-black                              |
  |               | skin-purple                             |
  |               | skin-yellow                             |
  |               | skin-red                                |
  |               | skin-green                              |
  |---------------------------------------------------------|
  |LAYOUT OPTIONS | fixed                                   |
  |               | layout-boxed                            |
  |               | layout-top-nav                          |
  |               | sidebar-collapse                        |
  |               | sidebar-mini                            |
  |---------------------------------------------------------|
  -->
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">

      <!-- Main Header -->
      <header class="main-header">
          <jsp:include page="common/header.jsp"></jsp:include> 
      </header>
      
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar">
        <section class="sidebar">
            <jsp:include page="common/sidebar.jsp"></jsp:include>
        </section>
      </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
            Page Header
            <small>Optional description</small>
          </h1>
          <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
            <li class="active">Here</li>
          </ol>
        </section>

        <!-- Main content -->
        <section class="content">
          <!-- Your Page Content Here -->
		  <table id="list"><tr><td></td></tr></table> 
          <div id="pager"></div> 
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->

      <!-- Main Footer -->
      <footer class="main-footer">
          <jsp:include page="common/footer.jsp"></jsp:include>
      </footer>
      
      <!-- Control Sidebar -->
      <aside class="control-sidebar control-sidebar-dark">
        <!-- Create the tabs -->
        <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
          <li class="active"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li>
          <li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a></li>
        </ul>
        <!-- Tab panes -->
        <div class="tab-content">
          <!-- Home tab content -->
          <div class="tab-pane active" id="control-sidebar-home-tab">
            <h3 class="control-sidebar-heading">Recent Activity</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript::;">
                  <i class="menu-icon fa fa-birthday-cake bg-red"></i>
                  <div class="menu-info">
                    <h4 class="control-sidebar-subheading">Langdon's Birthday</h4>
                    <p>Will be 23 on April 24th</p>
                  </div>
                </a>
              </li>
            </ul><!-- /.control-sidebar-menu -->

            <h3 class="control-sidebar-heading">Tasks Progress</h3>
            <ul class="control-sidebar-menu">
              <li>
                <a href="javascript::;">
                  <h4 class="control-sidebar-subheading">
                    Custom Template Design
                    <span class="label label-danger pull-right">70%</span>
                  </h4>
                  <div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-danger" style="width: 70%"></div>
                  </div>
                </a>
              </li>
            </ul><!-- /.control-sidebar-menu -->

          </div><!-- /.tab-pane -->
          <!-- Stats tab content -->
          <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div><!-- /.tab-pane -->
          <!-- Settings tab content -->
          <div class="tab-pane" id="control-sidebar-settings-tab">
            <form method="post">
              <h3 class="control-sidebar-heading">General Settings</h3>
              <div class="form-group">
                <label class="control-sidebar-subheading">
                  Report panel usage
                  <input type="checkbox" class="pull-right" checked>
                </label>
                <p>
                  Some information about this general settings option
                </p>
              </div><!-- /.form-group -->
            </form>
          </div><!-- /.tab-pane -->
        </div>
      </aside><!-- /.control-sidebar -->
      <!-- Add the sidebar's background. This div must be placed
           immediately after the control sidebar -->
      <div class="control-sidebar-bg"></div>
    </div><!-- ./wrapper -->
  </body>
</html>