<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <h1>Créer un nouveau Bouquet</h1>
</head>
<style>
    body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}

form {
    max-width: 400px;
    margin: 20px auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
    text-align: center;
    color: #333;
}

label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
}

input[type="text"] {
    width: 100%;
    padding: 8px;
    margin-bottom: 16px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
}

button {
    background-color: #4caf50;
    color: #fff;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

button:hover {
    background-color: #45a049;
}

</style>
<body>
    <form action="bouquet" method="post">
        <label for="nomBouquet">Nom de votre bouquet</label>
        <input name="nomBouquet" type="text" id="nomBouquet">
        
        <p>
            Type du bouquet
             <select name="tipe" id="">
                <option value="ref">De référence</option>
                <option value="ord">Ordinaire</option>
    
            </select>   
            </p>
        
          <p>
            Nombre travailleurs de base(si nécessaire)       
           <input name="nbtravailleurs" type="text" id="nomBouquet">
        </p>
    
        <p>
            Augmentation Relative      
           <input name="aug" type="text" id="nomBouquet">
        </p>
        <p><a href="index.html">Accueil</a></p>

        <input type="submit" value="Enregistrer">
    </form>

</body>
    
    
</body>
</html>