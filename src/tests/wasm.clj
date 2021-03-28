(def Root (Node))

(def test
  (Chain
   "test"
   :Looped
   "" (Wasm.Run "../../deps/wasm3/test/wasi/simple/test.wasm") (Log "r1")
   "" (Wasm.Run "../../deps/wasm3/test/wasi/simple/test.wasm" ["cat" "wasm.clj"]) (Log "r2")
   "" (Wasm.Run "../../deps/wasm3/test/lang/fib32.wasm" ["10"] :EntryPoint "fib") (Log "r3")
   "" (Wasm.Run "../../deps/wasm3/test/lang/fib32.wasm" ["20"] :EntryPoint "fib") (Log "r4")
   10 (Wasm.Asm
       (pr-str
        '(module
          (type $i64_=>_i64 (func (param i64) (result i64)))
          (memory $0 0)
          (export "fib" (func $module/fib))
          (export "memory" (memory $0))
          (func $module/fib (param $0 i64) (result i64)
                (local $1 i32)
                (local $2 i32)
                (local $3 i32)
                i32.const 1
                local.set $1
                local.get $0
                i64.const 0
                i64.gt_s
                if
                loop $while-continue|0
                local.get $0
                i64.const 1
                i64.sub
                local.tee $0
                i64.const 0
                i64.ne
                if
                local.get $1
                local.get $2
                i32.add
                local.get $1
                local.set $2
                local.set $1
                br $while-continue|0
                end
                end
                local.get $1
                i64.extend_i32_s
                return
                end
                i64.const 0))) "fib") (Log) (Assert.Is 55 true)
   ))

(schedule Root test)
(run Root 0.1 10)