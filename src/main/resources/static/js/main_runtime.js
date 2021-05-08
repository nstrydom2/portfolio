var getRandomNumber = function(min, max) {
    return Math.random() * (max - min) + min;
}

// sleep time expects milliseconds
var sleep = function(time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}

var greetingTextEffect = async function() {
    var linksElement = document.getElementById("mylinks");
    linksElement.style.visibility = "collapse";

    var welcomeElement = document.getElementById("welcome");
    var welcomeText = welcomeElement.innerHTML;

    //console.log(welcomeText);
    try {
        if (!checkCookie()) {
            setCookie(30);

            welcomeElement.innerHTML = "|";
            await sleep(1000);

            for (const c of welcomeText) {
                welcomeElement.innerHTML = welcomeElement.innerHTML.replace("|", "");
                welcomeElement.innerHTML += c + "|";

                var randomTypoNum = getRandomNumber(7, 12);
                if ((welcomeElement.innerHTML.length % 12) == 0) {
                    var randomChar = String.fromCharCode(getRandomNumber(1, 26) + 96);

                    welcomeElement.innerHTML = welcomeElement.innerHTML.replace("|", "");
                    welcomeElement.innerHTML += randomChar + "|";

                    await sleep(getRandomNumber(60, 140));

                    welcomeElement.innerHTML = welcomeElement.innerHTML.replace("|", "");
                    welcomeElement.innerHTML = welcomeElement.innerHTML.slice(0, -1) + "|";
                }
                await sleep(getRandomNumber(90, 175));
            }
        }
    } catch (err) {

    } finally {
        linksElement.style.visibility = "visible";
        blinkCursorEffect(welcomeElement);
    }
}

var blinkCursorEffect = async function(welcomeElement) {
    while (true) {
        welcomeElement.innerHTML = welcomeElement.innerHTML.slice(0, -1) + "_";
        await sleep(1250);

        welcomeElement.innerHTML = welcomeElement.innerHTML.slice(0, -1) + "|";
        await sleep(1250);
    }
}

var isProjects = false;
var isResume = false;

var toggleTable = function() {
    var content = document.getElementById("content");
    var projects = document.getElementById("projects");

    if (!isProjects) {
        content.innerHTML = "";
        content.innerHTML = projects.innerHTML;
        isProjects = true;

        return;
    }

    content.innerHTML = "";
    isProjects = false;
}

var toggleResume = function() {
    var content = document.getElementById("content");
    var resume = document.getElementById("resume");

    if (!isResume) {
        content.innerHTML = "";
        content.innerHTML = resume.innerHTML;
        isResume = true;

        return;
    }

    content.innerHTML = "";
    isResume = false;
}

var linkHoverEffect = function(element) {
    element.innerHTML = element.innerHTML.replace("-", "[");
    element.innerHTML = element.innerHTML.replace("-", "]");
}

var defaultText = function(element) {
    element.innerHTML = element.innerHTML.replace("[", "-");
    element.innerHTML = element.innerHTML.replace("]", "-");
}

var dateInPast = function(firstDate, secondDate) {
  if(firstDate.setHours(0,0,0,0) <= secondDate.setHours(0,0,0,0)) {
    return true;
  }

  return false;
}

var setCookie = function(exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = expires + ";SameSite=None;path=/";
}

var getCookie = function(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }

        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }

    return "";
}

var checkCookie = function() {
    var expires = getCookie("expires");
    if (expires == "" || expires == null) {
        return false;
    }

    return true;
}