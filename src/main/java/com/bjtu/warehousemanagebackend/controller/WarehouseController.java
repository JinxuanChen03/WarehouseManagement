package com.bjtu.warehousemanagebackend.controller;



import com.bjtu.warehousemanagebackend.entity.Warehouse;
import com.bjtu.warehousemanagebackend.service.impl.WarehouseServiceImpl;
import com.bjtu.warehousemanagebackend.utils.Result;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {
    @Autowired
    private WarehouseServiceImpl warehouseService;

    /**
     * 新增一个仓库
     * @param warehouse
     * @return
     */
    @PostMapping
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public ResponseEntity<Result> addWareHouse(@RequestBody @Valid Warehouse warehouse){
        warehouseService.addWareHouse(warehouse);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    /**
     * 更新仓库信息
     * @param id
     * @param warehouse
     * @return
     */
    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public ResponseEntity<Result> updateWarehouse(@PathVariable("id") String id, @RequestBody @Valid Warehouse warehouse) {
        warehouse.setId(id);
        warehouseService.updateWarehouse(warehouse);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }

    /**
     * 获取一条仓库信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Result> getWarehouseById(@PathVariable("id") String id) {
        return new ResponseEntity<>(Result.success(warehouseService.getWarehouseById(id)), HttpStatus.OK);
    }

    /**
     * 模糊查询仓库名称
     * @param name
     * @return
     */
    @GetMapping("/search/{name}")
    public ResponseEntity<Result> searchByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(Result.success(warehouseService.searchByName(name)), HttpStatus.OK);
    }

    /**
     * 获取全部仓库信息
     * @return
     */
    @GetMapping
    public ResponseEntity<Result> getAll() {
        return new ResponseEntity<>(Result.success(warehouseService.getAll()), HttpStatus.OK);
    }

    /**
     * 删除仓库
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_ADMIN')")
    public ResponseEntity<Result> deleteWarehouse(@PathVariable("id") String id) {
        warehouseService.deleteWarehouse(id);
        return new ResponseEntity<>(Result.success(), HttpStatus.OK);
    }
}
