<?php
/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the installation.
 * You don't have to use the web site, you can copy this file to "wp-config.php"
 * and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * MySQL settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://wordpress.org/support/article/editing-wp-config-php/
 *
 * @package WordPress
 */

// ** MySQL settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define( 'DB_NAME', 'wordpress' );

/** MySQL database username */
define( 'DB_USER', 'user_wordpress' );

/** MySQL database password */
define( 'DB_PASSWORD', '%myU_w3sql' );

/** MySQL hostname */
define( 'DB_HOST', 'localhost' );

/** Database charset to use in creating database tables. */
define( 'DB_CHARSET', 'utf8mb4' );

/** The database collate type. Don't change this if in doubt. */
define( 'DB_COLLATE', '' );

/**#@+
 * Authentication unique keys and salts.
 *
 * Change these to different unique phrases! You can generate these using
 * the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}.
 *
 * You can change these at any point in time to invalidate all existing cookies.
 * This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define( 'AUTH_KEY',         'k8f7zgZf. gV}Y22{4yYxYCj7bM1_Bl:57ldV.u3<-~n]VC.rl2GY-R,okHP,yl|' );
define( 'SECURE_AUTH_KEY',  'IR?.rU2McSk2}v$XL@c;fz;!=@Fy/(o~[>{P^k1BPOzIo3b}Zo74VH2MI3b>kA8a' );
define( 'LOGGED_IN_KEY',    '^9 1_;@^.BbdHBPbb?aY_2uY)zBJGSKqmh`Cc2jay3[N=f`pHqed+w[W(#xG(/k$' );
define( 'NONCE_KEY',        '-HMN[xnEN0A=2|Ad/9],mlUUC%TU_Z~s<L=%Vb8?|3QGKWIS`4pq&Nh^-^^[7f4o' );
define( 'AUTH_SALT',        'vfW?yUwFG>yk2Cy>wC8i[|4/Q~$@vvGdm<!/o*Su^|{Mci#IpnA6/LTn`Lf$(1B7' );
define( 'SECURE_AUTH_SALT', '#c}c[J/d%[|ng16E#kM>7{%MY32@i.z2ma,;kbCw1(tV pli]I(uqfN~m#,D@HON' );
define( 'LOGGED_IN_SALT',   'h}n_ucCFQc;:D(gi[ULrU5QoRx)JzXAi.Mu0e!q<[@KCxKhE#5#}*RHS+!+jS>cd' );
define( 'NONCE_SALT',       'v}/#PS?sO=a!9cyNwvIN^+mJ(%SA]xgP-]-K^)JAOW(W@|hRu4[]/bKPw8wYL ,D' );

/**#@-*/

/**
 * WordPress database table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 */
$table_prefix = 'mkdwp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://wordpress.org/support/article/debugging-in-wordpress/
 */
define( 'WP_DEBUG', false );

/* Add any custom values between this line and the "stop editing" line. */



/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if ( ! defined( 'ABSPATH' ) ) {
	define( 'ABSPATH', __DIR__ . '/' );
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';


/* Desactivar cron wordpress */
define('DISABLE_WP_CRON', true);


/* Implement Cookie with HTTPOnly and Secure flag in WordPress */
@ini_set('session.cookie_httponly', true);
@ini_set('session.cookie_secure', true);
@ini_set('session.use_only_cookies', true);

/* X-Frame-Options */
header('X-Frame-Options: SAMEORIGIN');

/* Quitar actualizaciones automaticas */
define( 'WP_AUTO_UPDATE_CORE', false );


define('DISALLOW_FILE_EDIT', true);
define('FORCE_SSL_ADMIN', true);
