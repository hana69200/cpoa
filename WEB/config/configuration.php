<?php
 
const DEBUG = true; // production : false; dev : true

// Accès base de données
const BD_HOST = 'iutdoua-web.univ-lyon1.fr';
const BD_DBNAME = 'p1804713';
const BD_USER = 'p1804713';
const BD_PWD = '367876';

// Langue du site
const LANG ='FR-fr';

//Connexion
//constante de test login
const LOGADMIN='kiki';
// contante password cryptee
const MDPADMIN='$2y$10$va9WuT947Hbgc9HYKZSZ7.7E1uXgLmW1AZKqzMzTcy4M9LCt0nIbu';
const ADMIN='administrateur';
const MDP='admin';

//login administrateur
const LOGADMIN2='admin';
const MDPADMIN2='$2y$10$8mOKCUiyOvV5iv18Iv6nKuvc5n9Gtk1.UTg3Cp77qb2k/KmczsYqO';

// Paramètres du site : nom de l'auteur ou des auteurs
const AUTEUR = 'HMB';

//dossiers racines du site
define('PATH_CONTROLLERS','./controllers/c_');
define('PATH_ENTITY','./entities/');
define('PATH_ASSETS','./assets/');
define('PATH_LIB','./lib/');
define('PATH_MODELS','./models/');
define('PATH_VIEWS','./views/v_');
define('PATH_TEXTES','./languages/');

//sous dossiers
define('PATH_CSS', PATH_ASSETS.'css/');
define('PATH_IMAGES', PATH_ASSETS.'images/');
define('PATH_SCRIPTS', PATH_ASSETS.'scripts/');

//fichiers
define('PATH_LOGO', PATH_IMAGES.'logo.jpg');
define('PATH_MAIL', PATH_IMAGES.'mail.jpg');
define('PATH_FAX', PATH_IMAGES.'fax.png');
define('PATH_APPEL', PATH_IMAGES.'appel.png');
define('PATH_ACTION', PATH_IMAGES.'action.jpg');
define('PATH_TERRAIN', PATH_IMAGES.'terrain.jpg');
define('PATH_LOGO2', PATH_IMAGES.'homme.png');
define('PATH_MENU', PATH_VIEWS.'menu.php');
define('PATH_PLAN', PATH_IMAGES.'openTennis.jpg');
