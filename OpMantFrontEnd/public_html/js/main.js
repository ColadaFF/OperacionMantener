require.config({
    paths: {
        hbs: 'js/hbs/hbs',
        jquery: 'js/libs/jquery/jquery',
        i18nprecompile: 'js/hbs/hbs/i18nprecompile',
        json2: 'js/hbs/hbs/json2',
        underscore: 'js/hbs/hbs/underscore',
        ui: 'js/libs/jquery/jquery-ui',
        bootstrap: 'js/libs/twitter-bootstrap/js/bootstrap',
        hourpicker: 'js/libs/twitter-bootstrap/js/bootstrap-hourpicker',
        datepicker: 'js/libs/twitter-bootstrap/js/bootstrap-datepicker',
        text: 'js/text'
    },
    shim: {
        'ui': ['jquery'],
        'bootstrap': ['jquery'],
        'hourpicker': ['jquery'],
        'datepicker': ['jquery']
    }
});
var lastone = '';
var app = {
    facultyListURL: 'http://localhost:8080/OpMantBackend/rest/faculty/list',
    userListURL: 'http://localhost:8080/OpMantBackend/rest/user/list',
    classListURL: 'http://localhost:8080/OpMantBackend/rest/class/list',
    absenceListURL: 'http://localhost:8080/OpMantBackend/rest/absence/list?witch=1',
    //Save
    addfacultyURL: 'http://localhost:8080/OpMantBackend/rest/faculty/save',
    addClassURL: 'http://localhost:8080/OpMantBackend/rest/class/save',
    addAbsenceURL: 'http://localhost:8080/OpMantBackend/rest/absence/save',
    Evaluation: {},
    Class: {},
    Absence: {},
    init: function () {
        var _self = this;
        require(['jquery', 'hbs!./templates/login', 'bootstrap'], function ($, logintmpl) {
            $('#appContainer').html(logintmpl());
            $('#login').click(function (e) {
                e.preventDefault();
                _self.navigateToHome();
            });
        });
    },
    navigateToHome: function () {
        var _self = this;
        require(['jquery', 'hbs!./templates/home', 'hbs!./templates/faculty', 'hbs!./templates/absences', 'hbs!./templates/classes', 'ui', 'bootstrap'], function ($, hometmpl, facultytmpl, absencestmpl, classestmpl) {
            $('#appContainer').html(hometmpl({faculty: facultytmpl, absences: absencestmpl, classes: classestmpl}));
            _self.loadListClass('', function (values) {
                $('.tbody-class').html('');
                for (var idx in values) {
                    $('.tbody-class').append('<tr><td><a href="#" field-value="' + values[idx].id_class + '">' + values[idx].title + '</a></td>' +
                            '<td><a href="#">' + values[idx].date + '</a></td>' +
                            '<td><a href="#">' + values[idx].hour + '</a></td>' +
                            '<td><a href="#">' + values[idx].classroom + '</a></td>' +
                            '<td><a href="#">' + values[idx].teacher + '</a></td>' +
                            '</tr>');
                }
            });
            _self.loadListAbsence('', function (tvalues) {
                $('.tbody-absence').html('');
                for (var idx in tvalues) {
                    $('.tbody-absence').append('<tr><td><a href="#" field-value="' + tvalues[idx].id_absence + '">' + tvalues[idx].nameClass + '</a></td>' +
                            '<td><a href="#">' + tvalues[idx].nameTeacher + '</a></td>' +
                            '<td><a href="#">' + tvalues[idx].nameReplace + '</a></td>' +
                            '</tr>');
                }
            });
            var availableTagsTeacher = new Array();
            var value = '';
            _self.loadListTeacher('1', function (values) {
                for (var idx in values) {
                    value = values[idx].name;
                    availableTagsTeacher.push(value);
                }
            });
            $("#teacher-name").autocomplete({
                source: availableTagsTeacher
            });
            var availableTagsReplace = new Array();
            var value = '';
            _self.loadListTeacher('1', function (values) {
                for (var idx in values) {
                    value = values[idx].name;
                    availableTagsReplace.push(value);
                }
            });
            $("#replace-name").autocomplete({
                source: availableTagsReplace
            });
            var availableTagsClass = new Array();
            var value = '';
            _self.loadListClass('', function (values) {
                for (var idx in values) {
                    value = values[idx].title;
                    availableTagsClass.push(value);
                }
            });
            $("#class-name").autocomplete({
                source: availableTagsClass
            });


            $('#faculty-btn').click(function (e) {
                //Linea para limpiar la lista de las áreas al momento en que se selecciona un proceso

                $('.tbody-faculty').html('');
                _self.loadListFaculty(function (values) {
                    e.preventDefault();
                    console.log("something");
                    console.log(values);
                    for (var idx in values) {
                        $('.tbody-faculty').append('<tr><td><a href="#" field-value="' + values[idx].id_faulty + '">' + values[idx].name_faculty + '</a></td></tr>');

                    }
                });
            });
            $('#add-faculty-btn').click(function (e) {
                e.preventDefault();
                var name = $('#faculty-name').val();
                console.log(name);
                _self.setFaculty(name);
            });
            $('#add-absence-btn').click(function (e) {
                e.preventDefault();
                _self.Absence.nameClass = $('#class-name').val();
                _self.Absence.nameTeacher = $('#teacher-name').val();
                _self.Absence.nameReplace = $('#replace-name').val();
                _self.setAbsence(_self.Absence);
            });


            $('#class-btn').click(function (e) {
                e.preventDefault();
                _self.navigateToclass();
            });
        });
    },
    navigateToclass: function () {
        var _self = this;
        require(['jquery', 'hbs!./templates/class', 'hbs!./templates/classes', 'ui', 'bootstrap', 'datepicker', 'hourpicker'], function ($, classtmpl, classestmpl) {
            _self.loadListClass('', function (values) {
                $('#appContainer').html(classtmpl({classes: classestmpl}));
                for (var idx in values) {
                    $('.tbody-class').append('<tr><td><a href="#" field-value="' + values[idx].id_class + '">' + values[idx].title + '</a></td>' +
                            '<td><a href="#">' + values[idx].date + '</a></td>' +
                            '<td><a href="#">' + values[idx].hour + '</a></td>' +
                            '<td><a href="#">' + values[idx].classroom + '</a></td>' +
                            '<td><a href="#">' + values[idx].teacher + '</a></td>' +
                            '</tr>');

                }
                console.log(JSON.stringify(values));
                var selectedValuetp = '';
                var selectedValueArea = '';
                var selectedValueActividad = '';
                var selectedValueTarea = '';

                $('#class-btn').click(function (e) {
                    e.preventDefault();
                    _self.navigateToclass();
                });

                var availableTags = new Array();
                var value = '';
                _self.loadListTeacher('1', function (values) {
                    for (var idx in values) {
                        value = values[idx].name;
                        availableTags.push(value);
                    }
                });
                $("#teacher").autocomplete({
                    source: availableTags
                });
                $('#date-class').datepicker();
                $("#hour-class").timepicki();
                $('#add-faculty-btn').click(function (e) {
                    e.preventDefault();
                    var name = $('#faculty-name').val();
                    -
                            _self.setFaculty(name);
                });


                $('#save-class').click(function (e) {
                    e.preventDefault();
                    _self.Class.title = $('#title').val();
                    _self.Class.teacher = $('#teacher').val();
                    _self.Class.hour = $('#hour-class').val();
                    _self.Class.date = $('#date-class').val();
                    _self.Class.classroom = $('#classroom').val();
                    console.log(JSON.stringify(_self.Class));
                    _self.setClass(_self.Class);
                });
                $('#init, #faculty-btn').click(function (e) {
                    e.preventDefault();
                    _self.navigateToHome();
                });
            });
        });
    },
    loadList: function (listId, callback, dependentList, dependentValue) {
        $.ajax({
            url: app.listServiceURL,
            type: 'GET',
            async: true,
            data: {id: listId, d_list: dependentList, d_value: dependentValue}, success: function (data, textStatus, jqXHR) {
                callback(data);
            },
            error: function (xhr, options, error) {
                alert('Error: ' + error);
            }
        });
    }, loadListFaculty: function (callback) {
        $.ajax({
            url: app.facultyListURL,
            type: 'GET',
            async: true, success: function (data, textStatus, jqXHR) {
                callback(data);
            },
            error: function (xhr, options, error) {
                alert('Error: ' + error);
            }
        });
    },
    loadListTeacher: function (id, callback) {
        $.ajax({
            url: app.userListURL,
            type: 'GET',
            data: id,
            async: false,
            success: function (data, textStatus, jqXHR) {
                callback(data);
            },
            error: function (xhr, options, error) {
                alert('Error: ' + error);
            }
        });
    },
    loadListAbsence: function (id, callback) {
        $.ajax({
            url: app.absenceListURL,
            type: 'GET',
            data: id,
            async: false,
            success: function (data, textStatus, jqXHR) {
                callback(data);
            },
            error: function (xhr, options, error) {
                alert('Error: ' + error);
            }
        });
    },
    loadListClass: function (id, callback) {
        $.ajax({
            url: app.classListURL,
            type: 'GET',
            data: id,
            async: false,
            success: function (data, textStatus, jqXHR) {
                callback(data);
            },
            error: function (xhr, options, error) {
                alert('Error: ' + error);
            }
        });
    },
    setFaculty: function (name, callback) {
        $.ajax({
            url: app.addfacultyURL,
            type: 'POST',
            data: name,
            contentType: 'text/plain', success: function (data, textStatus, jqXHR) {
                alert("Datos insertados correctamente.");
            },
            error: function (xhr, options, error) {
                alert('Error: ' + error);
            }
        });
    },
    setClass: function (classes, callback) {
        $.ajax({
            url: app.addClassURL,
            type: 'POST',
            data: JSON.stringify(classes),
            contentType: 'application/json; charset=ISO-8859-1',
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                alert("Datos insertados correctamente.");
            },
            error: function (xhr, options, error) {
                alert('Error: ' + error);
            }
        });
    },
    setAbsence: function (absence, callback) {
        $.ajax({
            url: app.addAbsenceURL,
            type: 'POST',
            data: JSON.stringify(absence),
            contentType: 'application/json; charset=ISO-8859-1',
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                alert("Datos insertados correctamente.");
            },
            error: function (xhr, options, error) {
                alert('Error: ' + error);
            }
        });
    },
    setEvaluation: function (evaluation, callback) {
        $.ajax({
            url: app.listServiceURLSave,
            type: 'POST',
            data: JSON.stringify(evaluation),
            contentType: 'application/json; charset=ISO-8859-1',
            dataType: 'json', success: function (data, textStatus, jqXHR) {
                alert("Datos insertados correctamente.");
            },
            error: function (xhr, options, error) {
                alert('Error: ' + error);
            }
        });
    }

};