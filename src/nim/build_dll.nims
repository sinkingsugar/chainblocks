when defined build: # prevent nimsuggest execution...
  when defined windows:
    exec "nim cpp --newruntime --app:lib --passC:-ggdb -d:forceCBRuntime -f -o:../py/chainblocks/chainblocks.dll chainblocks.nim"