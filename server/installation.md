# Server Installation
## Server and Dev
Ensure that you have pip installed first.

- Then install Flask using: `pip install Flask`
  - Need pip on linux? `apt-get install python-pip`



## Just Server
1. Configure: `sudo vim /etc/apache2/sites-available/LaceUp.conf`

  ```
  <VirtualHost *:80>
  		ServerName <<SERVER_NAME_HERE>>
  		ServerAdmin <<EMAIL@mywebsite.com>>
  		WSGIScriptAlias / /var/www/LaceUp/laceup.wsgi
  		<Directory /var/www/LaceUp/LaceUp/>
  			Order allow,deny
  			Allow from all
  		</Directory>
  		ErrorLog ${APACHE_LOG_DIR}/error.log
  		LogLevel warn
  		CustomLog ${APACHE_LOG_DIR}/access.log combined
  </VirtualHost>
  ```

2. Enable Virtual Host: `sudo a2ensite LaceUp`
3. Application goes in `/var/www/LaceUp`
  ```
    /var/www/LaceUp/
        LaceUp/
          __init__.py
        laceup.wsgi
  ```
4. restart apache: `sudo service apache2 restart`

## Dev
1. Run local: `python /LaceUp/LaceUp/__init__.py`
