<?php
include '../includes/header.php'; ?>
<h1>Connexion</h1>
<form method="POST">
    <input type="text" name="username" placeholder="Nom d'utilisateur" required><br>
    <input type="password" name="password" placeholder="Mot de passe" required><br>
    <button type="submit">Se connecter</button>
</form>
<?php include '../includes/footer.php';