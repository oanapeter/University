<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <form action="login.php" method="POST">
            <h2>Login</h2>
            <?php if (isset($_GET['error'])) { ?>
                <p class="error"><?php echo htmlspecialchars($_GET['error'], ENT_QUOTES, 'UTF-8'); ?></p>
            <?php } ?>
            <div>
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Enter username" required><br>
            </div>
            <div>
                <label for="password">Password</label>
                <input type="text" id="password" name="password" placeholder="Enter password" required><br>
            </div>
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
