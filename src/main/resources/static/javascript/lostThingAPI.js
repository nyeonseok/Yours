$(document).ready(function() {
    var selectedMarker = null; // 선택된 마커를 저장할 변수

    // kakaomap API 초기화
    var mapContainer = document.getElementById('map');
    var mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 서울시청 위치
        level: 5
    };
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 장소 검색 input에 대한 이벤트 리스너 추가
    $('#itemLocation').on('input', function() {
        var keyword = $(this).val();
        if (keyword !== '') {
            // kakaomap 장소 검색 서비스 생성
            var places = new kakao.maps.services.Places();

            // 키워드를 이용하여 장소 검색
            places.keywordSearch(keyword, function(result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    // 검색 결과가 있을 경우
                    var place = result[0];
                    var latlng = new kakao.maps.LatLng(place.y, place.x);
                    // 검색된 장소를 지도에 표시
                    var marker = new kakao.maps.Marker({
                        position: latlng
                    });
                    marker.setMap(map);
                    map.setCenter(latlng);

                    // 마커 클릭 이벤트 처리
                    kakao.maps.event.addListener(marker, 'click', function() {
                        if (selectedMarker) {
                            selectedMarker.setZIndex(0); // 이전에 선택되었던 마커의 Z-index를 변경
                        }
                        selectedMarker = marker;
                        selectedMarker.setZIndex(1); // 선택된 마커의 Z-index를 변경
                        // 선택한 장소의 정보를 HTML에 표시
                        $('#selectedPlaceName').text(place.place_name);
                        $('#selectedPlaceAddress').text(place.address_name);
                    });
                }
            });
        }
    });
});