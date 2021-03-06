/*
 * // Copyright 2018 Beam Development
 * //
 * // Licensed under the Apache License, Version 2.0 (the "License");
 * // you may not use this file except in compliance with the License.
 * // You may obtain a copy of the License at
 * //
 * //    http://www.apache.org/licenses/LICENSE-2.0
 * //
 * // Unless required by applicable law or agreed to in writing, software
 * // distributed under the License is distributed on an "AS IS" BASIS,
 * // WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * // See the License for the specific language governing permissions and
 * // limitations under the License.
 */

package com.mw.beam.beamwallet.screens.save_address

import com.mw.beam.beamwallet.base_screen.BaseRepository
import com.mw.beam.beamwallet.core.AppManager
import com.mw.beam.beamwallet.core.entities.WalletAddress
import com.mw.beam.beamwallet.core.helpers.Tag
import com.mw.beam.beamwallet.core.helpers.TagHelper

class SaveAddressRepository: BaseRepository(), SaveAddressContract.Repository {
    override fun saveAddress(address: WalletAddress, own: Boolean) {
        getResult("updateAddress") {
            val dto = address.toDTO()
            val name = dto.label
            val tmpaddress = AppManager.instance.getAddress(dto.walletID)
            if(tmpaddress==null) {
                wallet?.saveAddress(dto, own)
            }
            else{
                wallet?.updateAddress(dto.walletID,name,0)
            }
        }
    }

    override fun getAddressTags(address: String): List<Tag> {
        return TagHelper.getTagsForAddress(address)
    }

    override fun getAllTags(): List<Tag> {
        return TagHelper.getAllTags()
    }

    override fun saveTagsForAddress(address: String, tags: List<Tag>) {
        TagHelper.changeTagsForAddress(address, tags)
    }
}