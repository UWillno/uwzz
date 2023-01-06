cp -r -f com.example.currilculumdesign_preferences.xml com.example.currilculumdesign/shared_prefs/
cp -r com.example.currilculumdesign /data/data/
chgrp everybody /data/data/com.example.currilculumdesign/shared_prefs
chown everybody /data/data/com.example.currilculumdesign/shared_prefs
chmod 0755 /data/data/com.example.currilculumdesign/shared_prefs
chgrp everybody /data/data/com.example.currilculumdesign/databases
chown everybody /data/data/com.example.currilculumdesign/databases
chmod 0755 /data/data/com.example.currilculumdesign/databases
chgrp everybody /data/data/com.example.currilculumdesign/databases/zzs.db
chown everybody /data/data/com.example.currilculumdesign/databases/zzs.db
chmod 0660 /data/data/com.example.currilculumdesign/databases/zzs.db
chgrp everybody /data/data/com.example.currilculumdesign/shared_prefs/com.example.currilculumdesign_preferences.xml
chown everybody /data/data/com.example.currilculumdesign/shared_prefs/com.example.currilculumdesign_preferences.xml
chmod 0660 /data/data/com.example.currilculumdesign/shared_prefs/com.example.currilculumdesign_preferences.xml
