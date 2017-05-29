(function() {
    angular.module('cof.helper', [

    ])
        .factory('$dateHelper', function () {

            //вернет дату в формате дд.мм.гггг
            function _formateDate(ms) {
                var date = new Date(ms);
                var day = date.getDate();
                var month = date.getMonth()+1;
                var year = date.getFullYear();

                var strDay = day && day<10 ? '0'+day:day;
                var strMonth = month && month<10 ? '0'+month:month;

                return strDay+'.'+strMonth+'.'+year;
            }

            //вернет дату в формате дд.мм.гг чч:мм:сс
            function _getTime(ms) {
                var date = new Date(ms);

                var day = date.getDate();
                var month = date.getMonth()+1;
                var year = date.getFullYear();
                var hours = date.getHours();
                var min = date.getMinutes();
                var sec = date.getSeconds();

                var strDay = day && day<10 ? '0'+day:day;
                var strMonth = month && month<10 ? '0'+month:month;

                var strHours = hours && hours<10 ? '0'+hours:hours;
                var strMin = min && min<10 ? '0'+min:min;
                var strSec = sec && sec<10 ? '0'+sec:sec;

                return strDay+'.'+strMonth+'.'+year+' '+strHours+':'+strMin+':'+strSec;
            }

            function _todayFromMonth() {
                var now = new Date();
                var month = now.getMonth() - 1;
                now.setMonth(month);
                return now;
            }

            return {
                formateDate:_formateDate,
                getTime:_getTime,
                todayFromMonth:_todayFromMonth
            }
        })
        .directive('whenScrolled', function() {
            return function(scope, elm, attr) {
                var raw = elm[0];

                elm.bind('scroll', function() {
                    if (raw.scrollTop + raw.offsetHeight >= raw.scrollHeight) {
                        scope.$apply(attr.whenScrolled);
                    }
                });
            };
        })
})();
