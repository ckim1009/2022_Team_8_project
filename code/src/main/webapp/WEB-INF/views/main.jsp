<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>바로요약(Beta)</title>
</head>  

<body>

    <div class="container-fluid px-0">
        <div class="row d-flex justify-content-center">
            <div class="col-12 col-md-6 col-lg-5 mt-2 mx-0 px-4">
                <div class="d-flex flex-row justify-content-between align-items-center px-0">
                    <img src="/img/bi-08.png" alt="로고" onclick="location.reload()">
                    <div class="d-flex flex-row">
                        <!--<button class="btn btn-primary rounded me-2" onclick="btn_upload()" id="upload_btn">업로드</button>-->
                        <button class="btn btn-secondary rounded text-white" disabled id="progress_btn">대기중</button>
                    </div>
                    
                    <input type="file" id="upload_file" style="display:none" required onchange="btn_upload_change()">
                </div>
            </div>
			
        </div>
		<div id="first_layer">
			<div class="row d-flex justify-content-center mx-2">
				<div class="col-12 col-md-6 col-lg-5 mt-5 py-5" style="border: 4px dashed #000000">
					<div class="d-flex flex-col justify-content-center align-items-center py-1">
						<p class="my-0">요약 문서의 업로드 방식을</p>
					</div>
					<div class="d-flex flex-col justify-content-center align-items-center py-1">
						<p>아래의 버튼을 통해 선택해주세요</p>
					</div>
					<div class="d-flex flex-col justify-content-center align-items-center py-1">
						<button class="col-4 btn btn-primary" onclick="text_layer_open()">텍스트 입력</button>
					</div>
					<div class="d-flex flex-col justify-content-center align-items-center py-1">
						<button class="col-4 btn btn-primary" onclick="pdf_layer_open()">PDF 파일</button>
					</div>
					
				</div>
			</div>
		</div>

		<div id="text_layer" style="display: none;" >
			<div class="row d-flex justify-content-center mx-2" >
				<div class="col-12 col-md-6 col-lg-5 mt-0 py-5 px-2">
					<p class="text-center">텍스트를 입력하시고 업로드 버튼을 클릭해주세요.</p>
					<textarea class="w-100" rows="10" id="textbody"></textarea>
					<div class="row d-flex justify-content-center">
						<div class="col-12 col-md-6 col-lg-5 mt-3 py-0 px-0 d-flex justify-content-center">
							<button class="btn btn-primary me-1" onclick="text_upload()">업로드</button>
							<button class="btn btn-primary" onclick="location.reload()">취소</button>
						</div>
					</div>
				</div>		
			</div>
		
		</div>

		<div id="pdf_layer" style="display: none;">
			<div class="row d-flex justify-content-center py-5 mx-2">
				<div class="col-12 col-md-6 col-lg-5 py-5" style="border:4px dashed #000000;" id="file_box">
				  <p class="d-flex justify-content-center">파일을 드래그 해서 넣어주시거나</p>
				  <p class="d-flex justify-content-center">아래 버튼을 클릭해서 파일을 선택해주세요.</p>
				  <div class="d-flex justify-content-center"><img src="/img/uploadBtn.png" alt="" class="col-3" onclick="btn_upload()"/></div>
				</div>
			</div>
			
		</div>
				
		<div class="row flex-row justify-content-center">
            <div class="col-12 col-md-6 col-lg-5 mt-2" style="display:none" id="paper_compressed">

                
	                <p class="px-3">요약 결과!</p>
	                <div class="border border-dark mx-3 mb-2 px-1 rounded-2" id="result_title"
	                        style="min-height:30px; max-height:70px; font-size:14px;"></div>
	                <div class="border border-dark mx-3 px-1 rounded-2 mb-1" id="result_body"
	                        style="min-height:150px; max-height:400px; overflow-x :hidden; overflow-y :scroll; font-size:12px;">
	                </div>
            	<div class="d-flex flex-row mx-3">
	                <div class="btn btn-primary me-2" onclick="download_file()">다운로드</div>
	                <div class="btn btn-secondary " onclick="location.reload()">취소</div>
                </div>
            </div>
            
            <form method="post" action="/download" id="download_submit">
           		<input type="hidden" name ="title" id="down_title">
				<input type="hidden" name="body" id="down_body">
            </form>
            
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>