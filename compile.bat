set MakeResources=FALSE

IF %MakeResources%==TRUE (
	del /Q release\*
	copy data\images\* release\

	tools\pngout release\arrowdown.png
	tools\pngout release\arrowleft.png
	tools\pngout release\arrowright.png
	tools\pngout release\arrowup.png
	tools\pngout release\glowdownarrow.png
	tools\pngout release\glowleftarrow.png
	tools\pngout release\glowrightarrow.png
	tools\pngout release\glowuparrow.png
  tools\pngout release\Background.png
  tools\pngout release\LittleDownArrow_Blue.png
  tools\pngout release\LittleDownArrow_Green.png
  tools\pngout release\LittleDownArrow_Red.png
  tools\pngout release\LittleDownArrow_Yellow.png
  tools\pngout release\LittleLeftArrow_Blue.png
  tools\pngout release\LittleLeftArrow_Green.png
  tools\pngout release\LittleLeftArrow_Red.png
  tools\pngout release\LittleLeftArrow_Yellow.png
  tools\pngout release\LittleRightArrow_Blue.png
  tools\pngout release\LittleRightArrow_Green.png
  tools\pngout release\LittleRightArrow_Red.png
  tools\pngout release\LittleRightArrow_Yellow.png
  tools\pngout release\LittleUpArrow_Blue.png
  tools\pngout release\LittleUpArrow_Green.png
  tools\pngout release\LittleUpArrow_Red.png
  tools\pngout release\LittleUpArrow_Yellow.png
  tools\pngout release\ShadowDownArrow.png
  tools\pngout release\ShadowLeftArrow.png
  tools\pngout release\ShadowRightArrow.png
  tools\pngout release\ShadowUpArrow.png
  tools\pngout release\Dust.png

) ELSE (
	del /Q release\*.class
)

copy data\notes\* release\
javac -cp source\*.java source\*.java -d release\
call play.bat
