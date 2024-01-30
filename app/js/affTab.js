const getXHR = () => {
  try {
      return new ActiveXObject('Msxml2.XMLHTTP');
  } catch (e) {
      try {   
          return new ActiveXObject('Microsoft.XMLHTTP');
      } catch (e2) {
          try {
              return new XMLHttpRequest();  
          } catch (e3) {
              return false;
          }
      }
  }
};

function fetchAndParseJSON(url, callback) {
  xhr=getXHR();
  xhr.open('GET', url, true);
  
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      // Parse response text to JSON
      console.log(xhr.responseText);
      const jsonData = JSON.parse(xhr.responseText);
      // Call the callback function with the parsed JSON data
      callback(jsonData);
    }
  };

  xhr.send();
}
