a=$(ls -l /data/data/ | grep com.example.currilculumdesign)
b=${a:14:25}
c=${b% u*}
cp -r -f com.example.currilculumdesign_preferences.xml com.example.currilculumdesign/shared_prefs/
cp -r com.example.currilculumdesign /data/data/
chgrp $c /data/data/com.example.currilculumdesign/shared_prefs
chown $c /data/data/com.example.currilculumdesign/shared_prefs
chmod 0755 /data/data/com.example.currilculumdesign/shared_prefs
chgrp $c /data/data/com.example.currilculumdesign/databases
chown $c /data/data/com.example.currilculumdesign/databases
chmod 0755 /data/data/com.example.currilculumdesign/databases
chgrp $c /data/data/com.example.currilculumdesign/databases/zzs.db
chown $c /data/data/com.example.currilculumdesign/databases/zzs.db
chmod 0660 /data/data/com.example.currilculumdesign/databases/zzs.db
chgrp $c /data/data/com.example.currilculumdesign/shared_prefs/com.example.currilculumdesign_preferences.xml
chown $c /data/data/com.example.currilculumdesign/shared_prefs/com.example.currilculumdesign_preferences.xml
chmod 0660 /data/data/com.example.currilculumdesign/shared_prefs/com.example.currilculumdesign_preferences.xml
