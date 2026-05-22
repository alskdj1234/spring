<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
/* 	차트 비율 해제를 위한 제한 설정 */
	.flex-area > .container {
		padding:10px;
	}
	.flex-area > .container .chart-wrapper {
		position:relative;
		height:300px;
	}
</style>

<!-- chartjs CDN -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<!-- 차트를 그리는 함수 -->
<script type="text/javascript">
//데이터를 불러와서 차트를 그리는 함수
function createChart(endpoint, selector) {
    $.ajax({
        url: "/rest/stat/"+endpoint,
        method: "post",
        success: function (response) {//response에 titles와 values가 담겨있음
            const ctx = $(selector)[0];

            //차트 생성 - new Chart(태그, {옵션});
            new Chart(ctx, {

                //type은 차트의 유형 (bar/line/pie/doughnut)
                type: response.type,
                //data는 차트를 그리기 위한 값의 정보
                data: {
                    //labels가 x축에 표시될 이름
                    labels: response.titles,
                    //datasets은 차트에 그릴 데이터 집합들 (복수개 가능)
                    datasets: [
                        {
                            //label: '국가 수',//범례
                            data: response.values,//표시될 값
                            borderWidth: 1,//테두리두께
                            backgroundColor: [
                                "rgba(214, 48, 49, 0.5)",
                                "rgba(225, 112, 85, 0.5)",
                                "rgba(253, 203, 110, 0.5)",
                                "rgba(0, 184, 148, 0.5)",
                                "rgba(116, 185, 255, 0.5)",
                                "rgba(9, 132, 227, 0.5)",
                                "rgba(108, 92, 231, 0.5)"
                            ],
                            borderColor: [
                                "rgba(214, 48, 49,1.0)",
                                "rgba(225, 112, 85,1.0)",
                                "rgba(253, 203, 110,1.0)",
                                "rgba(0, 184, 148,1.0)",
                                "rgba(116, 185, 255,1.0)",
                                "rgba(9, 132, 227,1.0)",
                                "rgba(108, 92, 231,1.0)"
                            ],
                        }
                    ]
                },
                //options는 차트를 표시하기 위한 옵션
                options: {
                	responsive: true,
                	maintainAspectRatio: false,
                    scales: {
                        y: {
                            beginAtZero: true//y축을 무조건 0부터 시작하도록 처리
                        }
                    },
                    plugins: {
                        legend: { display: false }//범례 제거
                    },
                }
            });
        }
    });
}
</script>

<script type="text/javascript">
	$(function(){
		createChart("country-region", ".country-region");
		createChart("lecture-category", ".lecture-category");
		createChart("lecture-type", ".lecture-type");
		createChart("book-genre", ".book-genre");
		createChart("member-level", ".member-level");
		createChart("board-head", ".board-head");
	});
</script>

<div class="container w-950 mt-50 mb-50">
	<div class="cell center">
		<h1>대시보드</h1>
	</div>
	
	<div class="cell flex-area" style="flex-wrap: wrap;">
		<div class="container w-33 mb-30">
			<div class="cell">
				<h3>대륙별 국가 현황</h3>
			</div>
			<div class="cell chart-wrapper">
				<canvas class="country-region"></canvas>
			</div>
			<div class="cell right">
				<a href="#" class="link">
					<span>데이터 더 보기</span>
					<i class="fa-solid fa-arrow-right fa-fade"></i>
				</a>
			</div>
		</div>
		<div class="container w-33 mb-30">
			<div class="cell">
				<h3>주제별 강좌 현황</h3>
			</div>
			<div class="cell chart-wrapper">
				<canvas class="lecture-category"></canvas>
			</div>
			<div class="cell right">
				<a href="#" class="link">
					<span>데이터 더 보기</span>
					<i class="fa-solid fa-arrow-right fa-fade"></i>
				</a>
			</div>
		</div>
		<div class="container w-33 mb-30">
			<div class="cell">
				<h3>유형별 강좌 현황</h3>
			</div>
			<div class="cell chart-wrapper">
				<canvas class="lecture-type"></canvas>
			</div>
			<div class="cell right">
				<a href="#" class="link">
					<span>데이터 더 보기</span>
					<i class="fa-solid fa-arrow-right fa-fade"></i>
				</a>
			</div>
		</div>
		<div class="container w-33 mb-30">
			<div class="cell">
				<h3>장르별 도서 현황</h3>
			</div>
			<div class="cell chart-wrapper">
				<canvas class="book-genre"></canvas>
			</div>
			<div class="cell right">
				<a href="#" class="link">
					<span>데이터 더 보기</span>
					<i class="fa-solid fa-arrow-right fa-fade"></i>
				</a>
			</div>
		</div>
		<div class="container w-33 mb-30">
			<div class="cell">
				<h3>등급별 회원 현황</h3>
			</div>
			<div class="cell chart-wrapper">
				<canvas class="member-level"></canvas>
			</div>
			<div class="cell right">
				<a href="#" class="link">
					<span>데이터 더 보기</span>
					<i class="fa-solid fa-arrow-right fa-fade"></i>
				</a>
			</div>
		</div>
		<div class="container w-33 mb-30">
			<div class="cell">
				<h3>주제별 게시글 현황</h3>
			</div>
			<div class="cell chart-wrapper">
				<canvas class="board-head"></canvas>
			</div>
			<div class="cell right">
				<a href="#" class="link">
					<span>데이터 더 보기</span>
					<i class="fa-solid fa-arrow-right fa-fade"></i>
				</a>
			</div>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>