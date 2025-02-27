<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login-PizzaStore</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body{
                background: url(assess/2ffa5105e1ab6b4634eebdb911526acd.jpg) center center fixed;
                background-size: calc(50%);
            }
            .form-label{
                background: aquamarine;
                color: graytext;
            }
            .col-md-6.h3{
               align-content: center;
            }
            .col-md-6.p{
               align-content: center;
            }
        </style>
        
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-danger">
            <div class="container">
                <a class="navbar-brand" href="#">Fast Food Online</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="categoriesDropdown" role="button" data-bs-toggle="dropdown">
                                Categories
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="#">Food</a></li>
                                <li><a class="dropdown-item" href="#">Drink</a></li>
                                <li><a class="dropdown-item" href="#">Pizza</a></li>
                            </ul>
                        </li>
                        <li class="nav-item"><a class="nav-link" href="#">Reviews</a></li>
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item"><a class="nav-link" href="#">Register</a></li>
                        <li class="nav-item"><a class="nav-link" href="#">Log in</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Login Form -->
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6" style="background: whitesmoke">
                    <h3>Please log in here.</h3>
                    <p>Enter your details below</p>
                    <form>
                        <div class="mb-3">
                            <label for="username" class="form-label">User Name</label>
                            <input type="text" class="form-control" id="username" placeholder="Enter your username">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" placeholder="Enter your password">
                        </div>
                        <button type="submit" class="btn btn-primary">Log in</button>
                    </form>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
